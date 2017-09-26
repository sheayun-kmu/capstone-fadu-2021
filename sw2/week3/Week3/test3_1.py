import sys
fname = input("Enter data file name: ")
try:
	fH = open(fname)
except FileNotFoundError as e:
	print("No such file: " + fname)
	sys.exit()
	

wordcount = {}
for line in fH:
	words = line.split()
	
	for word in words:
		if word in wordcount:
			wordcount[word] += 1
		else:
			wordcount[word] = 1
fH.close()
for word in wordcount:
	print(word, ":", wordcount[word])

