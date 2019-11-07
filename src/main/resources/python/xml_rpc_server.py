import sys
import time

print("Hello World!")

from xmlrpc.server import SimpleXMLRPCServer

realtime_force_array = [0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
calibration_force_array = [0.0, 0.0, 0.0, 0.0, 0.0, 0.0]

def respon_string(str):
    print("inside function: respon_string")
    return "get string:%s" % str
    
def set_realtime_force(realtime_force):
    global realtime_force_array
    realtime_force_array = realtime_force
    print (realtime_force)
    return "ok"

def get_realtime_force():
    return realtime_force_array

if __name__ == '__main__':
    server = SimpleXMLRPCServer(('localhost', 8888))
    server.register_function(respon_string, "get_string")
    server.register_function(set_realtime_force, "set_realtime_force")
    server.register_function(get_realtime_force, "get_realtime_force")
    print ("Listening for Client")
    server.serve_forever()
    