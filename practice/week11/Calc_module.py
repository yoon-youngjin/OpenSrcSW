class Calc:

    my_name = "I'm Calc Module!"
    scale = 0

    def __init__(self, scale=1):
        self.scale = scale

    def __del__(self):
        self.scale = 1
        print("Scale is reset to 1.")

    def add(self, a, b):
        result = (a + b) * self.scale
        print("{0:f} + {1:f} = {2:f}".format(a,b,result))
    def sub(self, a, b):
        result = (a - b) * self.scale
        print("{0:f} - {1:f} = {2:f}".format(a,b,result))
    def mul(self, a, b):
        result = (a * b) * self.scale
        print("{0:f} * {1:f} = {2:f}".format(a,b,result))
    def div(self, a, b):
        if b != 0:
            result = (a / b) * self.scale
            print("{0:f} - {1:f} = {2:f}".format(a,b,result))

class ParentCalc:
    def add(self, a, b):
        result = a + b
        print("{0:f} + {1:f} = {2:f}".format(a,b,result))
    def sub(self, a, b):
        result = a - b
        print("{0:f} - {1:f} = {2:f}".format(a,b,result))

class ChildCalc(ParentCalc):
    def add(self, *a):
        s = "{0:f}".format(a[0])
        for i in a[1:]:
            s = s + " + " + "{0:f}".format(i)
        result = sum(a)
        print("{0:s} = {1:f}".format(s, result))

## 연산자 오버라이딩

class Point:
    def __init__(self,x,y):
        self.x = x
        self.y = y

    def __add__(self, other):
        self.x += other.x
        self.y += other.y
        return self

    def show(self):
        print("{0:d}, {1:d}".format(self.x, self.y))