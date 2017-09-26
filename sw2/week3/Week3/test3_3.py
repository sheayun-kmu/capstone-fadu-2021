dbfilename = 'test3_3.dat'

def readScoreDB():
	try:
		fH = open(dbfilename)
	except FileNotFoundError as e:
		print("New DB: ", dbfilename)
		return []
	else:
		print("Open DB: ", dbfilename)

	line = fH.read()
	scdb = eval(line)
	fH.close()
	return scdb


# write the data into person db
def writeScoreDB(scdb):
	fH = open(dbfilename, 'w')
	fH.write(str(scdb) + '\n')
	fH.close()

def doScoreDB(scdb):
	while(True):
		inputstr = (input("Score DB > "))
		if inputstr == "": continue
		parse = inputstr.split(" ")
		if parse[0] == 'add':
			record = {'Name':parse[1], 'Age':parse[2], 'Score':parse[3]}
			scdb += [record]
		elif parse[0] == 'del':
			for p in scdb:
				if p['Name'] == parse[1]:
					scdb.remove(p)	
					break
		elif parse[0] == 'show':
			sortKey ='Name' if len(parse) == 1 else parse[1]
			showScoreDB(scdb, sortKey)
		elif parse[0] == 'quit':
			break
		else:
			print("Invalid command: " + parse[0])
			

def showScoreDB(scdb, keyname):
	for p in sorted(scdb, key=lambda person: person[keyname]):
		for attr in sorted(p):
			print(attr + "=" + p[attr], end=' ')
		print()
	


scoredb = readScoreDB()
doScoreDB(scoredb)
writeScoreDB(scoredb)

