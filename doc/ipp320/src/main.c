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

int main(void) {
	CURL *curl_handle;
	CURLcode res;

	struct MemoryStruct chunk;

	chunk.memory = malloc(1); /* will be grown as needed by the realloc above */
	chunk.size = 0; /* no data at this point */

	curl_global_init(CURL_GLOBAL_DEFAULT);
	curl_handle = curl_easy_init();

	curl_easy_setopt(curl_handle, CURLOPT_URL,
			"https://qa-ams.active.com/MS/MSServer?TenderType=MERCHANT&TransactionType=GET&MerchantUser=emvca&MerchantPassword=emvca");
//			"https://qa-ams.active.com/MS/MSServer?TenderType=EMV&TransactionType=EMV_PARAM&MerchantUser=emvca&MerchantPassword=emvca&terminalNumber=036&paymentDeviceSerialNumber=PP814365PB063182");
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
	free(chunk.memory);
	curl_global_cleanup();

	return 0;
}
