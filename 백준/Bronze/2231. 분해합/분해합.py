def div_sum(n):
    ln = len(n)
    n = int(n)

    for i in range(n - (9 * ln), n):
        m = i
        num = i

        while m > 0:
            num += m % 10
            m = m // 10

        if num == n:
            print(i)
            return

    print(0)

n = input()
div_sum(n)