/*
 * main.c
 *
 *  Created on: 2017��11��11��
 *      Author: Administrator
 */

#include "ipp320.h"

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */

	char * fileName = "/Users/nickfeng/hub/fdj32/PTxCore/doc/131540886937708660.init.dat";
	int fileSize = getFileSize(fileName);
	printf("%d\n", fileSize);
	char * fileData = loadFile(fileName);
	int n = vegaInit(fileData);
	return EXIT_SUCCESS;
}
