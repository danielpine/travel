
i=1

for name in `ls`;
do
echo $((i=i+1))
newname=`printf "%05d.jpg" $i`
echo $newname
mv $name $newname
done
