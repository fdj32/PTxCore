//============================================================================
// Name        : serial.cpp
// Author      : Nick Feng
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
#include <boost/algorithm/hex.hpp>
#include <boost/asio/serial_port.hpp>
#include <boost/asio.hpp>
#include <iostream>
#include <iterator>
#include <string>
#include <vector>

using namespace std;
using namespace boost::asio;

int main() {
	cout << "!!!Hello World!!!" << endl; // prints !!!Hello World!!!
	boost::system::error_code ec;
	io_service io;
	serial_port port(io, "/dev/tty.usbmodem1411");
	port.set_option(serial_port_base::baud_rate(9600));
	port.set_option(serial_port_base::character_size(8));
	port.set_option(serial_port_base::stop_bits(serial_port_base::stop_bits::one));
	port.set_option(serial_port_base::parity(serial_port_base::parity::none));

	if(port.is_open()) {
		cout << "serial port opened" << endl;
	}

	string msg = "";

	string hex = "0235382e3030343148656c6c6f2c2020202020202020202020202020576f726c642120202020202020202020202020202020202020202020202020202020202020202020202020200328";

	msg = boost::algorithm::unhex(hex);

	cout << boost::algorithm::unhex(hex) << endl;

	boost::asio::write(port,buffer(msg));

	return 0;
}
