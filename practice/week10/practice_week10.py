import math
from math import sin

def module_prac():
    print(sin(3.14))

def list_add(list):
    print(sum(list))

def add(x=5, y=20):
    z = x + y
    print(z)

def swap(x, y):
    return (y, x)

# 가변형 인자
    ## 다수의 인자를 튜플이나 딕셔너리로 받는 것
    ## 튜플 가변형 인자: *args
    ## 딕셔너리 가변형 인자: **args

def scaled_add(c, *args):
    return c*sum(args)

def showme(*args):
    print(args)

def super_add(*args, **kwargs):
    c=kwargs.get('scale')
    o=kwargs.get('offset')
    return o+c*sum(args)

def search_phone(book, name):
    num = book[name]
    if num:
        return num
    else:
        return "none"



def showmemore(**kwargs):
    print(kwargs)

if __name__ == '__main__':
    add(10)
    add(10,10)
    add(y=10)

    print(swap(10,20))
    print(scaled_add(2,1,2,3))
    showme(1,2,3)

    print(super_add(1,2,3,scale=2, offset=0))
    showmemore(scale=2, offset=0)
    module_prac()

    n = int(input("사람 수?"))

    book = dict()

    for i in range(n):
        k = input("name=")
        v = input("phone=")
        book[k] = v

    k = input("검색할 이름?")
    v = search_phone(book, k)

    print("{0:s}'s phone number is {1:s}".format(k,v))


