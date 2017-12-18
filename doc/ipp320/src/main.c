#include "ipp320.h"

// https://curl.haxx.se/libcurl/c/getinmemory.html

struct MemoryStruct {
	char *memory;
	size_t size;
};

static size_t WriteMemoryCallback(void *contents, size_t size, size_t nmemb,
		void *userp) {
	size_t realsize = size * nmemb;
	struct MemoryStruct *mem = (struct MemoryStruct *) userp;

	mem->memory = realloc(mem->memory, mem->size + realsize + 1);
	if (mem->memory == NULL) {
		/* out of memory! */
		printf("not enough memory (realloc returned NULL)\n");
		return 0;
	}

	memcpy(&(mem->memory[mem->size]), contents, realsize);
	mem->size += realsize;
	mem->memory[mem->size] = 0;

	return realsize;
}

/**
 * http://xmlsoft.org/examples/tree1.c
 *
 * print_element_names:
 * @a_node: the initial xml node to consider.
 *
 * Prints the names of the all the xml elements
 * that are siblings or children of a given xml node.
 */
static void print_element_names(xmlNode * a_node) {
	xmlNode *cur_node = NULL;

	for (cur_node = a_node; cur_node; cur_node = cur_node->next) {
		if (cur_node->type == XML_ELEMENT_NODE) {
			printf("node type: Element, name: %s\n", cur_node->name);
		}

		if (cur_node->type == XML_TEXT_NODE) {
			printf("node type: Text, name: %s=%s\n", cur_node->name, cur_node->content);
		}

		print_element_names(cur_node->children);
	}
}

/**
 * http://xmlsoft.org/examples/parse3.c
 *
 * example3Func:
 * @content: the content of the document
 * @length: the length in bytes
 *
 * Parse the in memory document and free the resulting tree
 */
static void example3Func(const char *content, int length) {
	xmlDocPtr doc; /* the resulting document tree */

	/*
	 * The document being in memory, it have no base per RFC 2396,
	 * and the "noname.xml" argument will serve as its base.
	 */
	doc = xmlReadMemory(content, length, "noname.xml", NULL, 0);
	if (doc == NULL) {
		fprintf(stderr, "Failed to parse document\n");
		return;
	} else {
		/*Get the root element node */
		xmlNodePtr root_element = xmlDocGetRootElement(doc);
		print_element_names(root_element);
	}
	xmlFreeDoc(doc);
}

int main0(void) {
	CURL *curl_handle;
	CURLcode res;

	struct MemoryStruct chunk;

	chunk.memory = malloc(1); /* will be grown as needed by the realloc above */
	chunk.size = 0; /* no data at this point */

	curl_global_init(CURL_GLOBAL_DEFAULT);
	curl_handle = curl_easy_init();

	curl_easy_setopt(curl_handle, CURLOPT_URL,
//			"https://qa-ams.active.com/MS/MSServer?TenderType=MERCHANT&TransactionType=GET&MerchantUser=emvca&MerchantPassword=emvca");
			"https://qa-ams.active.com/MS/MSServer?TenderType=EMV&TransactionType=EMV_PARAM&MerchantUser=emvca&MerchantPassword=emvca&terminalNumber=036&paymentDeviceSerialNumber=PP814365PB063182");
	curl_easy_setopt(curl_handle, CURLOPT_WRITEFUNCTION, WriteMemoryCallback);
	curl_easy_setopt(curl_handle, CURLOPT_WRITEDATA, (void * )&chunk);

	/* some servers don't like requests that are made without a user-agent
	 field, so we provide one */
	curl_easy_setopt(curl_handle, CURLOPT_USERAGENT, "libcurl-agent/1.0");

	res = curl_easy_perform(curl_handle);
	if (res != CURLE_OK) {
		fprintf(stderr, "curl_easy_perform() failed: %s\n",
				curl_easy_strerror(res));
	} else {
		/*
		 * Now, our chunk.memory points to a memory block that is chunk.size
		 * bytes big and contains the remote file.
		 *
		 * Do something nice with it!
		 */
		printf("%lu bytes retrieved\n", (long) chunk.size);
		puts(chunk.memory);
	}
	curl_easy_cleanup(curl_handle);

	/*
	 * this initialize the library and check potential ABI mismatches
	 * between the version it was compiled for and the actual shared
	 * library used.
	 */
	LIBXML_TEST_VERSION
	example3Func(chunk.memory, chunk.size);

	/*
	 * Cleanup function for the XML library.
	 */
	xmlCleanupParser();
	/*
	 * this is to debug memory for regression tests
	 */
	xmlMemoryDump();

	free(chunk.memory);
	curl_global_cleanup();

	return 0;
}

void test_buildTerminalSpecificData() {
	TerminalSpecificData * o = buildTerminalSpecificData("ca", "700000200727   ", "00000036", "        ", "CA0C00_ApVis A000000025 A000000152 A000000277 A000000065 A000000004 A000000003\n");
	puts(TerminalSpecificDataToXML(o));
}

static Param * parseParam(const char *content, int length) {
	xmlDocPtr doc; /* the resulting document tree */

	/*
	 * The document being in memory, it have no base per RFC 2396,
	 * and the "noname.xml" argument will serve as its base.
	 */
	doc = xmlReadMemory(content, length, "noname.xml", NULL, 0);
	Param * param = NULL;
	if (doc == NULL) {
		fprintf(stderr, "Failed to parse document\n");
		return NULL;
	} else {
		/*Get the root element node */
		xmlNodePtr root_element = xmlDocGetRootElement(doc);
		param = malloc(sizeof(Param));
		BINRange * binRangePtr = param->binRangeHead; // empty
		RIDSetting * ridPtr = param->ridHead;
		CAKey * caKeyPtr = param->caKeyHead;
		xmlNode * node = root_element->children;
		xmlNode * nodePtr = NULL;
		if(strcmp(node->name, "BINRanges") == 0) {
			node = node->children;
			while(NULL != node) { // BINRange
				BINRange * binRangeTmp = malloc(sizeof(BINRange));
				nodePtr = node->children;
				while(NULL != nodePtr) {
					if(strcmp(nodePtr->name, "Card")) {
						binRangeTmp->card = nodePtr->content;
					} else if(strcmp(nodePtr->name, "Lengths")) {

					} else if(strcmp(nodePtr->name, "Prefixes")) {

					}
				}
				binRangePtr->next = binRangeTmp;
				binRangePtr = binRangeTmp;
				node = node->next;
			}
		} else if (strcmp(node->name, "PTxCoreSettings") == 0) {
			node = node->children;
			while(NULL != node) { // RID
				RIDSetting * ridTmp = malloc(sizeof(RIDSetting));
				nodePtr = node->children;
				while(NULL != nodePtr) {
					if(strcmp(nodePtr->name, "TACDenial")) {
						ridTmp->tacDenial = nodePtr->content;
					} else if(strcmp(nodePtr->name, "TACOnline")) {
						ridTmp->tacOnline = nodePtr->content;
					} else if(strcmp(nodePtr->name, "TACDefault")) {
						ridTmp->tacDefault = nodePtr->content;
					} else if(strcmp(nodePtr->name, "Value")) {
						ridTmp->value = nodePtr->content;
					} else if(strcmp(nodePtr->name, "Name")) {
						ridTmp->name = nodePtr->content;
					} else if(strcmp(nodePtr->name, "AID")) {
						nodePtr = node->children;
						if(strcmp(nodePtr->name, "EnableFallback")) {
							ridTmp->enableFallback = nodePtr->content;
						} else if(strcmp(nodePtr->name, "FloorLimit")) {
							ridTmp->floorLimit = atoi(nodePtr->content);
						} else if(strcmp(nodePtr->name, "Threshold")) {
							ridTmp->threshold = atoi(nodePtr->content);
						}
					}
				}
				ridPtr->next = ridTmp;
				ridPtr = ridTmp;
				node = node->next;
			}
		} else if (strcmp(node->name, "PTxCoreCAKeys") == 0) {
			node = node->children;
			while(NULL != node) { // CAKey
				CAKey * caKeyTmp = malloc(sizeof(CAKey));
				nodePtr = node->children;
				while(NULL != nodePtr) {
					if(strcmp(nodePtr->name, "RID")) {
						caKeyTmp->rid = nodePtr->content;
					} else if(strcmp(nodePtr->name, "Index")) {
						caKeyTmp->index = nodePtr->content[0];
					} else if(strcmp(nodePtr->name, "Modulus")) {
						caKeyTmp->modulus = nodePtr->content;
					} else if(strcmp(nodePtr->name, "Exponent")) {
						caKeyTmp->exponent = nodePtr->content[0];
					} else if(strcmp(nodePtr->name, "Hash")) {
						caKeyTmp->hash = nodePtr->content;
					}
				}
				caKeyPtr->next = caKeyTmp;
				caKeyPtr = caKeyTmp;
				node = node->next;
			}
		}
	}
	xmlFreeDoc(doc);
	return param;
}

void parseParamDown() {
	char * fileName = "/Users/nickfeng/hub/fdj32/PTxCore/doc/ipp320/doc/param_down.xml";

	int fileSize = getFileSize(fileName);
	printf("%d\n", fileSize);

	char * fileData = loadFile(fileName);

	puts(fileData);

	parseParam(fileData, fileSize);
}



int main(void) {
	parseParamDown();
}
