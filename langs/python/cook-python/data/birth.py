# coding: utf8

death = 75
end = 1000
baby = [22, 25]

cnt = [[i, 0] for i in range(death)]

cnt[12][1] = 100
# if death > 20:
#     cnt[20][1] = 100

for lp in range(end):
    acc = 0
    for k,v in cnt:
        if k in baby:
            cnt[0][1] += int(v / 2)
        cnt[k][1] = acc
        acc = v
    sm = 0
    for k, v in cnt:
        sm += v
    print(lp, '-', sm)

print(cnt)
sm = 0
for k, v in cnt:
    sm += v
print(sm)
