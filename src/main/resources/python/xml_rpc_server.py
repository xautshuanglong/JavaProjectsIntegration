import sys
import time

print("Hello World!")

from xmlrpc.server import SimpleXMLRPCServer

def respon_string(str):
    print("inside function: respon_string")
    return "get string:%s" % str


if __name__ == '__main__':
    server = SimpleXMLRPCServer(('localhost', 8888))
    server.register_function(respon_string, "get_string")
    print ("Listening for Client")
    server.serve_forever()