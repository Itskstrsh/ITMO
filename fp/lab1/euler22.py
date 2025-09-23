with open('lab1/names.txt', 'r') as f:
    s = f.read()
names = [t.strip('"') for t in s.split(',')]
names.sort()
ans = sum((i+1)*sum(ord(c)-64 for c in name if c.isalpha()) for i, name in enumerate(names))
print(ans)