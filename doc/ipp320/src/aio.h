/*
 * aio.h
 *
 *  Created on: Dec 1, 2017
 *      Author: nickfeng
 */

#ifndef AIO_H_
#define AIO_H_

#ifdef __cplusplus
extern "C" {
#endif

typedef struct Msg {
	char * msg;
	int len;
	struct Msg * next;
} Msg;

int openComPort();

void closeComPort();

void * recvMsg(Msg * h);

#ifdef __cplusplus
}
#endif

#endif /* AIO_H_ */
