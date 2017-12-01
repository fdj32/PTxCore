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

	pthread_t receiver;
	Msg * recvHead = malloc(sizeof(Msg));
	recvHead->next = NULL;
	pthread_create(&receiver, NULL, sayhello, recvHead);
	printf("hello, world! I'm father\n");
	usleep(100);
	if(NULL != recvHead->next) {
		printf("%s\n", recvHead->next->msg);
		free(recvHead->next);
		recvHead->next = NULL;
	}

	return EXIT_SUCCESS;
}
