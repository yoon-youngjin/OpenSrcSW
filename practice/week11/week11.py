import csv
import sys
from Calc_module import Calc
from Calc_module import ChildCalc
from Calc_module import Point

def copy():

    arg_num = len(sys.argv)
    if arg_num != 3:
        sys.exit()

    in_name = sys.argv[1]
    out_name = sys.argv[2]

    f = open(in_name, 'r')
    g = open(out_name, 'w')

    for line in f:
        g.write(line)
    f.close()
    g.close()




if __name__ == '__main__':

    f = open("myfile.txt", "w")
    f.write("1 2 3\n4 5 6\n7 8 9")
    f.close()

    f = open("myfile.txt", "r")
    for line in f:
        print(line)
    f.close()

    # f.readlines(): 읽기 모드로 열린 파일의 모든 라인을 한꺼번에 읽어서 리스트로 리턴
    f = open("myfile.txt", "r", encoding="utf-8")

    lines = f.readlines()
    print(lines)

    f.close()

    # CSV 파일 읽기

    f = open('seoul.csv', 'r', encoding='cp949')
    data = csv.reader(f)
    for row in data:
        print(row)

    f.close()

    # 파일 쓰기
    f = open('new.txt', 'w')
    for i in range(1,11):
        data = "{0:d} line\n".format(i)
        f.write(data)

    f.close()

    # 추가 모드로 파일쓰기
    f = open('new.txt', 'a')

    for i in range(11,21):
        data = "{0:d} line\n".format(i)
        f.write(data)

    f.close()

    for arg in sys.argv[1:]:
        print(arg + "-> "+ arg.upper())


    # !실습: Unix의 cp(copy) 명령어를 python으로 구현하시오
    # python cp.py file1 file2
    # copy()

    num = Calc(5)
    print(num.my_name)

    num.add(5,10)
    num.sub(5,10)
    num.mul(5,10)
    num.div(5,10)

    num2 = ChildCalc()
    num2.add(1,2,3,4)

    p1 = Point(10,20)
    p1.show()

    p2 = Point(30,40)
    p2.show()

    p1 = p1 + p2
    p1.show()

    ## 예외처리

    num1 = int(input("num1="))
    num2 = int(input("num2="))

    try:
        num3 = num1/num2
        print("{0:d} / {1:d} = {2:f}".format(num1,num2, num3))

    except ZeroDivisionError as e:
        print(e)
    finally:
        print("The end!")








