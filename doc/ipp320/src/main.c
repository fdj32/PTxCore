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
//		if (cur_node->type == XML_ELEMENT_NODE) {
//			printf("node type: Element, name: %s\n", cur_node->name);
//		}
//
//		if (cur_node->type == XML_TEXT_NODE) {
//			printf("node type: Text, name: %s=%s\n", cur_node->name, cur_node->content);
//		}
		printf("node type: %d, name: %s, content: %s, parent.name: %s\n", cur_node->type, cur_node->name, cur_node->content, cur_node->parent->name);

		if(NULL != cur_node->children)
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

void parseParamDown() {
	char * fileName = "/Users/nickfeng/hub/fdj32/PTxCore/doc/ipp320/doc/param_down.xml";

	int fileSize = getFileSize(fileName);
	printf("%d\n", fileSize);

	char * fileData = loadFile(fileName);

	puts(fileData);

	Param * p = parseParam(fileData, fileSize);
	puts(Param2string(p));
}

void parseStringList() {
	IntList * il = string2IntList("1,,-1,,,-20,30,102", ',');
	while(NULL != il->next) {
		printf("il->next->i=%d\n", il->next->i);
		il = il->next;
	}
}

int main(void) {
//	parseParamDown();
//	test_buildTerminalSpecificData();
	int a = sizeof("helloworld");
	int b = strlen("helloworld");
	printf("a=%d,b=%d\n", a, b);

	struct timeval   tv;
	gettimeofday(&tv, NULL);
	time_t sec = tv.tv_sec;
	struct tm tm;
	localtime_r(&sec, &tm);
	printf("tm.tm_gmtoff=%d, TimeZone=%c%02d%02d\n", tm.tm_gmtoff,
			tm.tm_gmtoff >= 0 ? '+' : '-',
					(int)((tm.tm_gmtoff / 60) / 60),
					(int)((tm.tm_gmtoff / 60) % 60));
}
