wordcount = {'dog':3, 'cat':5, 'tiger':3}
print (wordcount['dog'])
wordcount['dog'] += 1
word = input("Word: ")
print (wordcount[word])
for k in wordcount.keys():
	print (k)
for v in wordcount.values():
	print (v)
for k, v in wordcount.items():
	print (k, v)
