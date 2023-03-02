def del_star(arr, size):
    ds = size // 3

    if size == len(arr):
        for i in range(ds, ds * 2):
            for j in range(ds, ds * 2):
                arr[i][j] = ' '

        return del_star(arr, ds)

    else:
        for x in range(len(arr) // size):
            for y in range(len(arr) // size):
                for i in range(ds + x * size, ds * 2 + x * size):
                    for j in range(ds + y * size, ds * 2 + y * size):
                        arr[i][j] = ' '

        if ds > 1:
            return del_star(arr, ds)
        else:
            return

n = int(input())
stars = [['*'] * n for _ in range(n)]
del_star(stars, len(stars))

for i in range(len(stars)):
    print("".join(stars[i]))