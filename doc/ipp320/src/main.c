#include "ipp320.h"

int main(void) {
	CURL *curl_handle;
	CURLcode res;

	curl_global_init(CURL_GLOBAL_DEFAULT);
	curl_handle = curl_easy_init();

	curl_easy_setopt(curl_handle, CURLOPT_URL,
			"https://qa-ams.active.com/MS/MSServer?TenderType=MERCHANT&TransactionType=GET&MerchantUser=emvca&MerchantPassword=emvca");
//			"https://qa-ams.active.com/MS/MSServer?TenderType=EMV&TransactionType=EMV_PARAM&MerchantUser=emvca&MerchantPassword=emvca&terminalNumber=036&paymentDeviceSerialNumber=PP814365PB063182");
//	curl_easy_setopt(curl_handle, CURLOPT_WRITEFUNCTION, parseStreamCallback);
//	curl_easy_setopt(curl_handle, CURLOPT_WRITEDATA, (void * )parser);

	res = curl_easy_perform(curl_handle);
	if (res != CURLE_OK) {
		fprintf(stderr, "curl_easy_perform() failed: %s\n",
				curl_easy_strerror(res));
	}
	curl_easy_cleanup(curl_handle);
	curl_global_cleanup();

	return 0;
}
