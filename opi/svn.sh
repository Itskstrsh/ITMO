REPO_URL="file://$(pwd)/repo"

#инит репы
svnadmin create svn-usage #создаем репу
svn mkdir -m "create structure" $REPO_URL/trunk $REPO_URL/branches #создаем структуру проекта
svn checkout $REPO_URL/trunk workdir
cd workdir

#r0
cp ~/commits/commit0/* .
svn add *
svn commit -m "r0" --username=red

#r1
svn rm *  #удаляем файлы из рабочей директории (если это не сделать, то нельзя будет сделать коммит, т.к. новые файлы ничем не отличаются от старых)
cp ~/commits/commit1/* .
svn add *
svn commit -m "r1" --username=red

#r2
svn copy $REPO_URL/trunk $REPO_URL/branches/branch1 -m "created branch1"
svn switch $REPO_URL/branches/branch1
svn rm *
cp ~/commits/commit2/* .
svn add *
svn commit -m "r2" --username=blue

#r3
svn copy $REPO_URL/trunk $REPO_URL/branches/branch2 -m "created branch2"
svn switch $REPO_URL/branches/branch2
svn rm *
cp ~/commits/commit3/* .
svn add *
svn commit -m "r3" --username=blue

#r4
svn switch $REPO_URL/branches/branch1
svn rm *
cp ~/commits/commit4/* .
svn add *
svn commit -m "r4" --username=blue

#r5
svn switch $REPO_URL/branches/trunk
svn rm *
cp ~/commits/commit5/* .
svn add *
svn commit -m "r5" --username=red

#r6
svn rm *
cp ~/commits/commit6/* .
svn add *
svn commit -m "r6" --username=red

#r7
svn rm *
cp ~/commits/commit7/* .
svn add *
svn commit -m "r7" --username=red

#r8
svn rm *
cp ~/commits/commit8/* .
svn add *
svn commit -m "r8" --username=red

#r9
svn switch $REPO_URL/branches/branch1
svn rm *
cp ~/commits/commit9/* .
svn add *
svn commit -m "r9" --username=blue

#r10
svn switch $REPO_URL/branches/branch2
svn rm *
cp ~/commits/commit10/* .
svn add *
svn commit -m "r10" --username=blue

#r11
svn switch $REPO_URL/branches/branch1
svn merge $REPO_URL/branches/branch2
svn rm --force
cp ~/commits/commit11/* .
svn add *
svn commit -m "r11" --username=blue

#r12
svn switch $REPO_URL/branches/trunk
svn merge $REPO_URL/branches/branch1
svn rm --force
cp ~/commits/commit12/* .
svn add *
svn commit -m "r12" --username=red

#r13
svn rm *
cp ~/commits/commit13/* .
svn add *
svn commit -m "r13" --username=red

#r14
svn rm *
cp ~/commits/commit14/* .
svn add *
svn commit -m "r14" --username=red
