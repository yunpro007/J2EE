/**
 * 
 */

var addressInit = function(_cmbProvince, _cmbCity, defaultProvince, defaultCity)
{
	var cmbProvince = document.getElementById(_cmbProvince);
	var cmbCity = document.getElementById(_cmbCity);
	
	function cmbSelect(cmb, str)
	{
		for(var i=0; i<cmb.options.length; i++)
		{
			if(cmb.options[i].value == str)
			{
				cmb.selectedIndex = i;
				return;
			}
		}
	}
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
	}
	cmbSelect(cmbProvince, defaultProvince);
	changeProvince();
	cmbProvince.onchange = changeProvince;
}
