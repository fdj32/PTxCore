/*
 * main.c
 *
 *  Created on: 2017年11月11日
 *      Author: Administrator
 */

#include "ipp320.h"

void *sayhello(Msg * head) {
	printf("hello, world! I'm son\n");

	Msg * next = malloc(sizeof(Msg));
	next->msg = "China";
	next->len = 5;
	next->next = NULL;
	head->next = next;

}

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	/*
	 char * fileName = "/Users/nickfeng/hub/fdj32/PTxCore/doc/131540886937708660.init.dat";
	 int fileSize = getFileSize(fileName);
	 printf("%d\n", fileSize);
	 char * fileData = loadFile(fileName);
	 int n = vegaInit(fileData, fileSize);
	 */

	int n = openComPort();
	if(0 != n) {
		closeComPort();
		return EXIT_FAILURE;
	}

	pthread_t t;
	Msg * h = malloc(sizeof(Msg));
	h->next = NULL;
	pthread_create(&t, NULL, recvMsg, h);
	printf("hello, world! I'm father\n");

	char * recvBuf = malloc(128);
	memset(recvBuf, 0, 128);
	n = cpx58display01A('0', '0', '4', '1', "", "Initializing", "", "");

	usleep(300);
	if(NULL != h->next) {
		printf("got %s\n", h->next->msg);
		free(h->next);
		h->next = NULL;
	}

	closeComPort();

	return EXIT_SUCCESS;
}
