import sys
import time

from xmlrpc.server import SimpleXMLRPCServer

realtime_force_array = [0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
calibration_force_array = [0.0, 0.0, 0.0, 0.0, 0.0, 0.0]


def response_string(str):
    print("inside function: respon_string")
    return "get string:%s" % str


def set_realtime_force(realtime_force):
    global realtime_force_array
    realtime_force_array = realtime_force
    print(realtime_force)
    return "ok"


def get_realtime_force():
    return realtime_force_array


class MathHandler:
    def __init__(self):
        pass

    @staticmethod
    def add(a, b):
        return a + b

    @staticmethod
    def div(x, y):
        return x / y


class InstanceHandler:
    def __init__(self):
        self.result = 0

    def add(self, a, b):
        self.result = a + b
        return self.result

    def div(self, x, y):
        self.result = x / y
        return self.result


class ObjectHandler:
    def __init__(self):
        self.data = 0

    def set_data(self, data):
        self.data = data
        return "void"

    def get_data(self):
        return self.data


obj = ObjectHandler()

if __name__ == '__main__':
    server = SimpleXMLRPCServer(('localhost', 8888))
    server.register_multicall_functions()
    server.register_introspection_functions()
    server.register_function(response_string, "get_string")
    server.register_function(set_realtime_force, "set_realtime_force")
    server.register_function(get_realtime_force, "get_realtime_force")
    server.register_function(MathHandler.add, "math_add")
    server.register_function(MathHandler.div, "math_div")
    server.register_function(obj.set_data, "set_data")
    server.register_function(obj.get_data, "get_data")
    server.register_instance(InstanceHandler())

    print("Listening for Client")
    server.serve_forever()

    print("XML_RPC closed ...")
