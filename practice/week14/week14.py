
import sys
# from Calc_module import Calc
# from Calc_module import ChildCalc
import numpy as np
import csv
import matplotlib.pyplot as plt
import pandas as pd

if __name__ == '__main__':
    a = [1, 2, 3, 4]
    print(type(a), a)

    b = np.array(a)
    print(type(b), b)
    print(b.shape)

    # 인덱싱 및 슬라이싱
    print(a[1], a[-1], a[1:], a[::2])

    # 대표적인 numpy 메소드
    ## arange(): 리스트의 range와 동일
    print(np.arange(10))
    print(np.arange(1, 10, 2))
    print(np.arange(1, 2, 0.1))

    ## zeors(), ones(), eye()
    print(np.zeros(2))
    print(np.ones(2))
    print(np.eye(2))
    print(np.eye(2).shape)

    ## sqrt(), sin(), cos()
    print(np.sqrt(2))
    print(np.pi)
    print(np.sin(np.pi / 2))
    print(np.cos(0))

    ## random.rnad(), random.choice()
    print(np.random.rand(5))
    print(np.random.choice(10, 5))

    # n차원 행렬 만들기
    a = np.arange(10).reshape(2, 5)
    print(a)

    print(a.shape)
    print(a.ndim)  # 차원의 수 -> 2차원
    print(a.size)  # 원소 개수

    # 행렬 연산
    ## 행렬 pointwise 연산자: +, -, *, /
    a = np.array([[1, 2, 3],
                  [3, 2, 5]])
    b = np.array([[-1, 3, 5],
                  [1, 4, 2]])
    print(a + b)
    print(a - b)
    print(a * b)
    print(a / b)

    ## 곱(dot) 및 전치(transpose)
    a = np.array([[1, 2, 3],
                  [3, 2, 5]])
    b = np.array([[-1, 3, 5],
                  [1, 4, 2]])

    c = np.dot(a, b.transpose())  # (2 x 3) * (3 x 2) = (2 x 2)
    print(c)

    d = np.dot(a.T, b)
    print(d)  # (3 x 2) * (2 x 3) = (3 x 3)

    ## 한꺼번에 더하기/빼기
    a = np.zeros(5) + 2
    print(a)

    b = np.ones(5) - 2
    print(b)

    c = np.eye(3) + 1
    print(c)

    ## 마스크 씌우기
    a = np.arange(-3, 3)
    print(a)
    print(a[0])
    print(a < 0)  ## 마스크
    print(a[a < 0])  ## 마스크 결과 -> a[[t, t, t, f, f, f]]
    print(a[abs(a) > 2])  ## 마스크 결과 -> a[[t,f,f,f,f,f]]

    # pandas
    df = pd.DataFrame(np.random.rand(3, 2), index=['m1', 'm2', 'm3'], columns=['price', 'num'])
    print(df)

    ## 특정 열 접근 및 마스킹
    print(df, "\n")
    print(df['num'], "\n")
    print(df['num'] > 0.5, "\n")
    df2 = df[df['num'] > 0.5]
    print(df2.shape)
    print(df2.T)

    ## 컬럼 간 연산
    df['rate'] = df['price'] / df['num']
    print(df)

    ## 인덱스(행) 내 연산 - 기준 축을 기준으로 각 행이 numpy array로 취급
    print(np.sum(df, axis=1))
    df['sum'] = np.sum(df, axis=1)  # 행 -> axis=1, 열 -> axis=2
    print(df)

    ## file
    with open(f, encoding='utf-8') as file:
        cntWord = 0
        cntLine = 0
        for line in file:
            cntLine += 1
            for word in line.split():
                cntWord += 1
        print("라인 수:", cntLine, "\n단어 수:", cntWord)


    ## stack operation
    class Stack:
        def __init__(self):
            self.items = []

        def push(self, item):
            self.items.append(item)

        def pop(self):
            return self.items.pop()

        def top(self):
            return self.items[-1]

        def isEmpty(self):
            if len(self.items) == 0:
                return True


    num = Stack()
    op = Stack()
    operators = ['+', '-', "*", "/"]
    priority = {"+": 0, "-": 0, "*": 1, "/": 1}


    def calc(num, op):
        n2 = num.pop()
        n1 = num.pop()

        if op == '+':
            num.push(n1 + n2)

        if op == '-':
            num.push(n1 - n2)

        if op == '*':
            num.push(n1 * n2)

        if op == '/':
            num.push(n1 / n2)


    exp = input().split()

    for e in exp:
        if e not in operators:
            num.push(int(e))

        else:
            if not op.isEmpty() and priority[op.top()] >= priority[e]:
                calc(num, op.pop())
            op.push(e)

    while not op.isEmpty():
        calc(num, op.pop())

    print("".join(exp) + "=" + str(num.top()))


    ## 상속

    class Calc:
        my_name = "yoon"
        # 접근 제한자
        __scale = 0

        def __init__(self, scale=1):
            self.scale = scale

        def add(self, a, b):
            return self.scale * (a + b)

        def __del__(self):
            print("I'm out")


    class ChildCalc(Calc):

        def add(self, *a):
            return sum(a)

        ## 오류
        def showme(self):
            return super().scale

