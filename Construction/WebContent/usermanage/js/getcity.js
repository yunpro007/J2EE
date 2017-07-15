var rcity = function(_scity)
{
	var num = parseInt(_scity);
	for(var i=0; i<provinceList.length; i++)
	{
		if(parseInt(provinceList[i].val)>=num)
		{
			for(var j=0; j<provinceList[i].cityList.length; j++)
			{
				if(parseInt(provinceList[i].cityList[j].val) == num)
				{
					return provinceList[i].name + " " + provinceList[i].cityList[j].name;
				}
			}
		}
	}
}