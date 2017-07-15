var putaddressInit = function(_cmbProvince, _cmbCity, defnum)
{
	var cmbProvince = document.getElementById(_cmbProvince);
	var cmbCity = document.getElementById(_cmbCity);
	var num = parseInt(defnum); 
	
	function cmbAddOption(cmb, obj)
	{
		var option = document.createElement("OPTION");
		cmb.options.add(option);
		option.innerText = obj.name;
		option.value = obj.val;
		option.obj = obj;
	}
	
	function changeProvince()
	{
		cmbCity.options.length = 0;
		if(cmbProvince.selectedIndex == -1)return;
		var item = cmbProvince.options[cmbProvince.selectedIndex].obj;
		for(var i=0; i<item.cityList.length; i++)
		{
			cmbAddOption(cmbCity, item.cityList[i]);
		}
		cmbSelect(cmbCity, defaultCity);

	}


	for(var i=0; i<provinceList.length; i++)
	{
		cmbAddOption(cmbProvince, provinceList[i]);
		if(parseInt(provinceList[i].val)>=num)
		{
			for(var j=0; j<provinceList[i].cityList.length; j++)
			{
				if(parseInt(provinceList[i].cityList[j].val) == num)
				{
					cmbProvince.selectedIndex = i;
					cmbCity.options.length = 0;
					if(cmbProvince.selectedIndex == -1)return;
					var item = cmbProvince.options[i].obj;
					for(var k=0; k<item.cityList.length; k++)
					{
						cmbAddOption(cmbCity, item.cityList[k]);
					}
					cmbCity.selectedIndex = j;
				}
			}
		}
	}
	
	cmbProvince.onchange = changeProvince;
}

