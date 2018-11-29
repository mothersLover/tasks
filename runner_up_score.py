n = int(raw_input())
arr = map(int, raw_input().split())
arr.sort(reverse=True)
max_val = arr[0]
for val in arr:
    if val != max_val:
        print(val)
        break
