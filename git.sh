#init
git init git-usage
cd git-usage

#r0
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
unzip -o ../commits/commit0.zip -d src
git add .
git commit -m "r0"

#r1
unzip -o ../commits/commit1.zip -d src
git add .
git commit -m "r1"

#r2
git config --global user.name "blue"
git config --global user.mail "blue@itmo.ru"
git checkout -b branch1
unzip -o ../commits/commit2.zip -d src
git add .
git commit -m "r2"

#r3
git checkout -b branch2
unzip -o ../commits/commit3.zip -d src
git add .
git commit -m "r3"

#r4
git checkout branch1
unzip -o ../commits/commit4.zip -d src
git add .
git commit -m "r4"

#r5
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout master
unzip -o ../commits/commit5.zip -d src
git add .
git commit -m "r5"

#r6
unzip -o ../commits/commit6.zip -d src
git add .
git commit -m "r6"

#r7
unzip -o ../commits/commit7.zip -d src
git add .
git commit -m "r7"

#r8
unzip -o ../commits/commit8.zip -d src
git add .
git commit -m "r8"

#r9
git config --global user.name "blue"
git config --global user.mail "blue@itmo.ru"
git checkout branch1
unzip -o ../commits/commit9.zip -d src
git add .
git commit -m "r9"

#r10
git checkout branch2
unzip -o ../commits/commit10.zip -d src
git add .
git commit -m "r10"

#r11
git checkout branch1
git merge --no-commit branch2
unzip -o ../commits/commit11.zip -d src
git add .
git commit -m "r11"

#r12
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout master
git merge --no-commit branch1
unzip -o ../commits/commit12.zip -d src
git add .
git commit -m "r12"

#r13
unzip -o ../commits/commit12.zip -d src
git add .
git commit -m "r13"

#r14
unzip -o ../commits/commit14.zip -d src
git add .
git commit -m "r14"
