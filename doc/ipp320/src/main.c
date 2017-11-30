/*
 * main.c
 *
 *  Created on: 2017年11月11日
 *      Author: Administrator
 */

#include "ipp320.h"

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
/*
	char * fileName = "/Users/nickfeng/hub/fdj32/PTxCore/doc/131540886937708660.init.dat";
	int fileSize = getFileSize(fileName);
	printf("%d\n", fileSize);
	char * fileData = loadFile(fileName);
	int n = vegaInit(fileData);
*/


    char src[] = "你好";
    char dst[100];
    size_t srclen = 12;
    size_t dstlen = 12;

    fprintf(stderr,"in: %s\n",src);

    char * pIn = src;
    char * pOut = ( char*)dst;

    iconv_t conv = iconv_open("GBK", "UTF-8");
    size_t rc = iconv(conv, &pIn, &srclen, &pOut, &dstlen);
    iconv_close(conv);

    fprintf(stderr,"dstlen=%d\n", dstlen);
    dst[12 - dstlen] = 0;
    fprintf(stderr,"out: %s\n", dst);

    fprintf(stderr,"out: %d\n", strlen(dst));

    fprintf(stderr,"out: %s\n", hexByte(dst[0]));

    fprintf(stderr,"out: %s\n", hex(dst, 0, 4));

	return EXIT_SUCCESS;
}
