tx = []
with open('tx', 'r') as f:
    while True:
        lns = f.readline().strip()
        if not lns:
            break
        tx.append(float(lns))

# print(tx)
cnt = len(tx)
print('.99: ', tx[int(cnt * 0.99)])
print('.95: ', tx[int(cnt * 0.95)])
print('.90: ', tx[int(cnt * 0.90)])
print('.85: ', tx[int(cnt * 0.85)])
print('.80: ', tx[int(cnt * 0.80)])
print('.75: ', tx[int(cnt * 0.75)])
print('.70: ', tx[int(cnt * 0.70)])
print('.65: ', tx[int(cnt * 0.65)])
print('.60: ', tx[int(cnt * 0.60)])
print('.50: ', tx[int(cnt * 0.50)])
print('.40: ', tx[int(cnt * 0.40)])
print('.30: ', tx[int(cnt * 0.30)])
print('.20: ', tx[int(cnt * 0.20)])

th = [0.2, 0.5, 0.8, 1, 1.5, 2]
for i in th:
    j = 0
    for t in tx:
        if t > i:
            break
        j += 1
    print('threshold', i, ', count', cnt - j, ', percent', (cnt - j) / cnt * 100)
