import sys

dbfilename = 'test3_2.dat'
def readScoreDB():
	lines = []
	try:
		fH = open(dbfilename)
	except FileNotFoundError as e:
		print("New DB:", dbfilename)
	else:
		print("Open DB:", dbfilename)

	scdb = []
	for line in fH:
		dat = line.strip()
		person = dat.split(",")
		record = {}
		for str in person:
			kv = str.split(":")
			record[kv[0]] = kv[1]
		print (record)
		scdb += [record]
	fH.close()
	print(scdb)
	return scdb


# write the data into person db
def writeScoreDB(scdb):
	fH = open(dbfilename, 'w')
	for p in scdb:
		pinfo = []
		for attr in p:
			pinfo += [attr + ":" + p[attr]]
		print (pinfo)
		str = ','.join(pinfo)
		fH.write(str + '\n')
	fH.close()

def doScoreDB(scdb):
	while(True):
		dat = (input("Enter a new person: "))
		if dat == "": break
		person = dat.split(",")
		record = {'Name':person[0], 'Age':person[1], 'Height':person[2]}
		scdb += [record]
		cont = input("Continue (Y/n)")
		if cont != "Y": break
	print(scdb)
	for p in scdb:
		for attr in p:
#		print(p['Name'])
			print(attr + " : " + p[attr])
		print()
	

scoredb = readScoreDB()
doScoreDB(scoredb)
writeScoreDB(scoredb)

