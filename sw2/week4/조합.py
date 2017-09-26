#팩토리얼을 적용하여 조합구하기
def fact(n):
    while n == 0:
        return 1
    return n *fact(n-1)
def fac(x):
    while x ==0:
        return 1
    return x * fac(x-1)
def fa(y):
    while y == 0:
        return 1
    return y *fac(y-1)

a = int(input("더 큰 수를 입력하시오."))
b = int(input("더 작은 수를 입력하시오."))
c = a-b
d  = fac(b)
e = fa(c)
f = d*e
print (fact(a)/f)

