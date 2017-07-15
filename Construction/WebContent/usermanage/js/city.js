var provinceList = [
    {
    	name:"--",
    	val: '-1',
    	cityList:
    	[{ name : "  --  ", val : '-1'}]
    },
	{
		name:'北京',
		val : '16',
		cityList:
		[
			{ 
				name:'东城区',
				val : '1'
			},
			{
				name: '西城区',
				val: '2'
			},
			{
				name: '崇文区',
				val: '3'
			},
			{
				name: '宣武区',
				val: '4'
			},
			{
				name: '朝阳区',
				val: '5'
			},
			{
				name: '丰台区',
				val: '6'
			},
			{
				name: '石景山区',
				val: '7'
			},
			{
				name: '海淀区',
				val: '8'
			},
			{
				name: '门头沟区',
				val: '9'
			},
			{
				name: '房山区',
				val: '10'
			},
			{
				name: '通州区',
				val: '11'
			},
			{
				name: '顺义区',
				val: '12'
			},
			{
				name: '昌平区',
				val: '13'
			},
			{
				name: '大兴区',
				val: '14'
			},
			{
				name: '怀柔区',
				val: '14'
			},
			{
				name: '平谷区',
				val: '14'
			},
			{
				name: '密云县',
				val: '15'
			},
			{
				name: '延庆县',
				val: '16'
			}
				
		]
	},
	{
		name:'上海', 
		val : '35',
		cityList:
		[
			{
				name: '黄浦区',
				val: '17'
			},
			{
				name: '卢湾区',
				val: '18'
			},
			{
				name: '徐汇区',
				val: '19'
			},
			{
				name: '长宁区',
				val: '20'
			},
			{
				name: '静安区',
				val: '21'
			},
			{
				name: '普陀区',
				val: '22'
			},
			{
				name: '闸北区',
				val: '23'
			},
			{
				name: '虹口区',
				val: '24'
			},
			{
				name: '杨浦区',
				val: '25'
			},
			{
				name: '闵行区',
				val: '26'
			},
			{
				name: '宝山区',
				val: '27'
			},
			{
				name: '嘉定区',
				val: '28'
			},
			{
				name: '浦东新区',
				val: '29'
			},
			{
				name: '金山区',
				val: '30'
			},
			{
				name: '松江区',
				val: '31'
			},
			{
				name: '青浦区',
				val: '32'
			},
			{
				name: '南汇区',
				val: '33'
			},
			{
				name: '奉贤区',
				val: '34'
			},
			{
				name: '崇明县',
				val: '35'
			}
		]
	},
	{
		name:'天津', 
		val : '53',
		cityList:
		[
			{
				name: '和平区',
				val: '36'
			},
			{
				name: '河东区',
				val: '37'
			},
			{
				name: '河西区',
				val: '38'
			},
			{
				name: '南开区',
				val: '39'
			},
			{
				name: '河北区',
				val: '40'
			},
			{
				name: '红桥区',
				val: '41'
			},
			{
				name: '塘沽区',
				val: '42'
			},
			{
				name: '汉沽区',
				val: '43'
			},
			{
				name: '大港区',
				val: '44'
			},
			{
				name: '东丽区',
				val: '45'
			},
			{
				name: '西青区',
				val: '46'
			},
			{
				name: '津南区',
				val: '47'
			},
			{
				name: '北辰区',
				val: '48'
			},
			{
				name: '武清区',
				val: '49'
			},
			{
				name: '宝坻区',
				val: '50'
			},
			{
				name: '宁河县',
				val: '51'
			},
			{
				name: '静海县',
				val: '52'
			},
			{
				name: '蓟　县',
				val: '53'
			}
		]
	},
	{
		name:'广东', 
		val : '73',
		cityList:
		[
			{
				name: '东莞市',
				val: '54'
			},
			{
				name: '广州市',
				val: '55'
			},
			{
				name: '中山市',
				val: '56'
			},
			{
				name: '深圳市',
				val: '57'
			},
			{
				name: '惠州市',
				val: '58'
			},
			{
				name: '江门市',
				val: '59'
			},
			{
				name: '珠海市',
				val: '60'
			},
			{
				name: '汕头市',
				val: '61'
			},
			{
				name: '佛山市',
				val: '62'
			},
			{
				name: '湛江市',
				val: '63'
			},
			{
				name: '河源市',
				val: '64'
			},
			{
				name: '肇庆市',
				val: '65'
			},
			{
				name: '清远市',
				val: ''
			},
			{
				name: '潮州市',
				val: '66'
			},
			{
				name: '韶关市',
				val: '67'
			},
			{
				name: '揭阳市',
				val: '68'
			},
			{
				name: '阳江市',
				val: '69'
			},
			{
				name: '梅州市',
				val: '70'
			},
			{
				name: '云浮市',
				val: '71'
			},
			{
				name: '茂名市',
				val: '72'
			},
			{
				name: '汕尾市',
				val: '73'
			}
		]
	},
	{
		name:'山东', 
		val : '90',
		cityList:
		[			{
				name: '济南市',
				val: '74'
			},
			{
				name: '青岛市',
				val: '75'
			},
			{
				name: '临沂市',
				val: '76'
			},
			{
				name: '济宁市',
				val: '77'
			},
			{
				name: '菏泽市',
				val: '78'
			},
			{
				name: '烟台市',
				val: '79'
			},
			{
				name: '淄博市',
				val: '80'
			},
			{
				name: '泰安市',
				val: '81'
			},
			{
				name: '潍坊市',
				val: '82'
			},
			{
				name: '日照市',
				val: '83'
			},
			{
				name: '威海市',
				val: '84'
			},
			{
				name: '滨州市',
				val: '85'
			},
			{
				name: '东营市',
				val: '86'
			},
			{
				name: '聊城市',
				val: '87'
			},
			{
				name: '德州市',
				val: '88'
			},
			{
				name: '莱芜市',
				val: '89'
			},
			{
				name: '枣庄市',
				val: '90'
			},
		]
	},
	{
		name:'江苏省', 
		val : '103',
		cityList:
		[
			{
				name: '苏州市',
				val: '91'
			},
			{
				name: '徐州市',
				val: '92'
			},
			{
				name: '盐城市',
				val: '93'
			},
			{
				name: '无锡市',
				val: '94'
			},
			{
				name: '南京市',
				val: '95'
			},
			{
				name: '南通市',
				val: '96'
			},
			{
				name: '连云港市',
				val: '97'
			},
			{
				name: '常州市',
				val: '98'
			},
			{
				name: '镇江市',
				val: '99'
			},
			{
				name: '扬州市',
				val: '100'
			},
			{
				name: '淮安市',
				val: '101'
			},
			{
				name: '泰州市',
				val: '102'
			},
			{
				name: '宿迁市',
				val: '103'
			}

		]
	},
	{
		name:'河南省', 
		val : '121',
		cityList:
		[
			{
				name: '郑州市',
				val: '104'
			},
			{
				name: '南阳市',
				val: '105'
			},
			{
				name: '新乡市',
				val: '106'
			},
			{
				name: '安阳市',
				val: '107'
			},
			{
				name: '洛阳市',
				val: '108'
			},
			{
				name: '信阳市',
				val: '109'
			},
			{
				name: '平顶山市',
				val: '110'
			},
			{
				name: '周口市',
				val: '111'
			},
			{
				name: '商丘市',
				val: '112'
			},
			{
				name: '开封市',
				val: '113'
			},
			{
				name: '焦作市',
				val: '114'
			},
			{
				name: '驻马店市',
				val: '115'
			},
			{
				name: '濮阳市',
				val: '116'
			},
			{
				name: '三门峡市',
				val: '117'
			},
			{
				name: '漯河市',
				val: '118'
			},
			{
				name: '许昌市',
				val: '119'
			},
			{
				name: '鹤壁市',
				val: '120'
			},
			{
				name: '济源市',
				val: '121'
			}
		]
	},
	{
		name:'河北省', 
		val : '133',
		cityList:
		[
			{
				name: '石家庄市',
				val: '122'
			},
			{
				name: '唐山市',
				val: '123'
			},
			{
				name: '保定市',
				val: '124'
			},
			{
				name: '邯郸市',
				val: '125'
			},
			{
				name: '邢台市',
				val: '126'
			},
			{
				name: '河北区',
				val: '127'
			},
			{
				name: '沧州市',
				val: '128'
			},
			{
				name: '秦皇岛市',
				val: '129'
			},
			{
				name: '张家口市',
				val: '130'
			},
			{
				name: '衡水市',
				val: '131'
			},
			{
				name: '廊坊市',
				val: '132'
			},
			{
				name: '承德市',
				val: '133'
			}
		]
	},
	{
		name:'浙江省', 
		val : '144',
		cityList:
		[
			{
				name: '温州市',
				val: '134'
			},
			{
				name: '宁波市',
				val: '135'
			},
			{
				name: '杭州市',
				val: '136'
			},
			{
				name: '台州市',
				val: '137'
			},
			{
				name: '嘉兴市',
				val: '138'
			},
			{
				name: '金华市',
				val: '139'
			},
			{
				name: '湖州市',
				val: '140'
			},
			{
				name: '绍兴市',
				val: '141'
			},
			{
				name: '舟山市',
				val: '142'
			},
			{
				name: '丽水市',
				val: '143'
			},
			{
				name: '衢州市',
				val: '144'
			}
		]
	},
	{
		name:'香港', 
		val : '145',
		cityList:
		[
			{
				name: '香港特别行政区',
				val: '145'
			}
		]
	},
	{
		name:'陕西省', 
		val : '155',
		cityList:
		[
			{
				name: '西安市',
				val: '146'
			},
			{
				name: '咸阳市',
				val: '147'
			},
			{
				name: '宝鸡市',
				val: '148'
			},
			{
				name: '汉中市',
				val: '149'
			},
			{
				name: '渭南市',
				val: '150'
			},
			{
				name: '安康市',
				val: '151'
			},
			{
				name: '榆林市',
				val: '152'
			},
			{
				name: '商洛市',
				val: '153'
			},
			{
				name: '延安市',
				val: '154'
			},
			{
				name: '铜川市',
				val: '155'
			}
		]
	},
	{
		name:'湖南', 
		val : '169',
		cityList:
		[	
			{
				name: '长沙市',
				val: '156'
			},
			{
				name: '邵阳市',
				val: '157'
			},
			{
				name: '常德市',
				val: '158'
			},
			{
				name: '衡阳市',
				val: '159'
			},
			{
				name: '株洲市',
				val: '160'
			},
			{
				name: '湘潭市',
				val: '161'
			},
			{
				name: '永州市',
				val: '162'
			},
			{
				name: '岳阳市',
				val: '163'
			},
			{
				name: '怀化市',
				val: '164'
			},
			{
				name: '郴州市',
				val: '165'
			},
			{
				name: '娄底市',
				val: '166'
			},
			{
				name: '益阳市',
				val: '167'
			},
			{
				name: '张家界市',
				val: '168'
			},
			{
				name: '湘西州',
				val: '169'
			}
		]
	},
	{
		name:'重庆', 
		val : '209',
		cityList:
		[
			{
				name: '江北区',
				val: '170'
			},
			{
				name: '渝北区',
				val: '171'
			},
			{
				name: '沙坪坝区',
				val: '172'
			},
			{
				name: '九龙坡区',
				val: '173'
			},
			{
				name: '万州区',
				val: '174'
			},
			{
				name: '永川市',
				val: '175'
			},
			{
				name: '南岸区',
				val: '176'
			},
			{
				name: '酉阳县',
				val: '177'
			},
			{
				name: '北碚区',
				val: '178'
			},
			{
				name: '涪陵区',
				val: '179'
			},
			{
				name: '秀山县',
				val: '180'
			},
			{
				name: '巴南区',
				val: '181'
			},
			{
				name: '渝中区',
				val: '182'
			},
			{
				name: '石柱县',
				val: '183'
			},
			{
				name: '忠县',
				val: '184'
			},
			{
				name: '合川市',
				val: '185'
			},
			{
				name: '大渡口区',
				val: '186'
			},
			{
				name: '开县',
				val: '187'
			},
			{
				name: '长寿区',
				val: '188'
			},
			{
				name: '荣昌县',
				val: '189'
			},
			{
				name: '云阳县',
				val: '190'
			},
			{
				name: '梁平县',
				val: '191'
			},
			{
				name: '潼南县',
				val: '192'
			},
			{
				name: '江津市',
				val: '193'
			},
			{
				name: '彭水县',
				val: '194'
			},
			{
				name: '綦江县',
				val: '195'
			},
			{
				name: '璧山县',
				val: '196'
			},
			{
				name: '黔江区',
				val: '197'
			},
			{
				name: '大足县',
				val: '198'
			},
			{
				name: '巫山县',
				val: '199'
			},
			{
				name: '巫溪县',
				val: '200'
			},
			{
				name: '垫江县',
				val: '201'
			},
			{
				name: '丰都县',
				val: '202'
			},
			{
				name: '武隆县',
				val: '203'
			},
			{
				name: '万盛区',
				val: '204'
			},
			{
				name: '铜梁县',
				val: '205'
			},
			{
				name: '南川市',
				val: '206'
			},
			{
				name: '奉节县',
				val: '207'
			},
			{
				name: '双桥区',
				val: '208'
			},
			{
				name: '城口县',
				val: '209'
			}
		]
	},
	{
		name:'福建', 
		val : '218',
		cityList:
		[
			{
				name: '漳州市',
				val: '210'
			},
			{
				name: '厦门市',
				val: '211'
			},
			{
				name: '泉州市',
				val: '212'
			},
			{
				name: '福州市',
				val: '213'
			},
			{
				name: '莆田市',
				val: '214'
			},
			{
				name: '宁德市',
				val: '215'
			},
			{
				name: '三明市',
				val: '216'
			},
			{
				name: '南平市',
				val: '217'
			},
			{
				name: '龙岩市',
				val: '218'
			}
		]
	},
	{
		name:'云南', 
		val : '234',
		cityList:
		[
			{
				name: '昆明市',
				val: '219'
			},
			{
				name: '红河州',
				val: '220'
			},
			{
				name: '大理州',
				val: '221'
			},
			{
				name: '文山州',
				val: '222'
			},
			{
				name: '德宏州',
				val: '223'
			},
			{
				name: '曲靖市',
				val: '224'
			},
			{
				name: '昭通市',
				val: '225'
			},
			{
				name: '楚雄州',
				val: '226'
			},
			{
				name: '保山市',
				val: '227'
			},
			{
				name: '玉溪市',
				val: '228'
			},
			{
				name: '丽江地区',
				val: '229'
			},
			{
				name: '临沧地区',
				val: '230'
			},
			{
				name: '思茅地区',
				val: '231'
			},
			{
				name: '西双版纳州',
				val: '232'
			},
			{
				name: '怒江州',
				val: '233'
			},
			{
				name: '迪庆州',
				val: '234'
			}
		]
	},
	{
		name:'四川', 
		val : '155',
		cityList:
		[
			{
				name: '成都市',
				val: '235'
			},
			{
				name: '绵阳市',
				val: '236'
			},
			{
				name: '广元市',
				val: '237'
			},
			{
				name: '达州市',
				val: '238'
			},
			{
				name: '南充市',
				val: '239'
			},
			{
				name: '德阳市',
				val: '240'
			},
			{
				name: '广安市',
				val: '241'
			},
			{
				name: '阿坝州',
				val: '242'
			},
			{
				name: '巴中市',
				val: '243'
			},
			{
				name: '遂宁市',
				val: '244'
			},
			{
				name: '内江市',
				val: '245'
			},
			{
				name: '凉山州',
				val: '246'
			},
			{
				name: '攀枝花市',
				val: '247'
			},
			{
				name: '乐山市',
				val: '248'
			},
			{
				name: '自贡市',
				val: '249'
			},
			{
				name: '泸州市',
				val: '250'
			},
			{
				name: '雅安市',
				val: '251'
			},
			{
				name: '宜宾市',
				val: '252'
			},
			{
				name: '资阳市',
				val: '253'
			},
			{
				name: '眉山市',
				val: '254'
			},
			{
				name: '甘孜州',
				val: '255'
			}
		]
	},
	{
		name:'广西', 
		val : '269',
		cityList:
		[
			{
				name: '贵港市',
				val: '256'
			},
			{
				name: '玉林市',
				val: '257'
			},
			{
				name: '北海市',
				val: '258'
			},
			{
				name: '南宁市',
				val: '259'
			},
			{
				name: '柳州市',
				val: '260'
			},
			{
				name: '桂林市',
				val: '261'
			},
			{
				name: '梧州市',
				val: '262'
			},
			{
				name: '钦州市',
				val: '263'
			},
			{
				name: '来宾市',
				val: '264'
			},
			{
				name: '河池市',
				val: '265'
			},
			{
				name: '百色市',
				val: '266'
			},
			{
				name: '贺州市',
				val: '267'
			},
			{
				name: '崇左市',
				val: '268'
			},
			{
				name: '防城港市',
				val: '269'
			}
		]
	},
	{
		name:'安徽', 
		val : '286',
		cityList:
		[
			{
				name: '芜湖市',
				val: '270'
			},
			{
				name: '合肥市',
				val: '271'
			},
			{
				name: '六安市',
				val: '272'
			},
			{
				name: '宿州市',
				val: '273'
			},
			{
				name: '阜阳市',
				val: '274'
			},
			{
				name: '安庆市',
				val: '275'
			},
			{
				name: '马鞍山市',
				val: '276'
			},
			{
				name: '蚌埠市',
				val: '277'
			},
			{
				name: '淮北市',
				val: '278'
			},
			{
				name: '淮南市',
				val: '279'
			},
			{
				name: '宣城市',
				val: '280'
			},
			{
				name: '黄山市',
				val: '281'
			},
			{
				name: '铜陵市',
				val: '282'
			},
			{
				name: '亳州市',
				val: '283'
			},
			{
				name: '池州市',
				val: '284'
			},
			{
				name: '巢湖市',
				val: '285'
			},
			{
				name: '滁州市',
				val: '286'
			}
		]
	},
	{
		name:'海南', 
		val : '304',
		cityList:
		[
			{
				name: '三亚市',
				val: '287'
			},
			{
				name: '海口市',
				val: '288'
			},
			{
				name: '琼海市',
				val: '289'
			},
			{
				name: '文昌市',
				val: '290'
			},
			{
				name: '东方市',
				val: '291'
			},
			{
				name: '昌江县',
				val: '292'
			},
			{
				name: '陵水县',
				val: '293'
			},
			{
				name: '乐东县',
				val: '294'
			},
			{
				name: '保亭县',
				val: '295'
			},
			{
				name: '五指山市',
				val: '296'
			},
			{
				name: '澄迈县',
				val: '297'
			},
			{
				name: '万宁市',
				val: '298'
			},
			{
				name: '儋州市',
				val: '299'
			},
			{
				name: '临高县',
				val: '300'
			},
			{
				name: '白沙县',
				val: '301'
			},
			{
				name: '定安县',
				val: '302'
			},
			{
				name: '琼中县',
				val: '303'
			},
			{
				name: '屯昌县',
				val: '304'
			}
		]
	},
	{
		name:'江西', 
		val : '315',
		cityList:
		[
			{
				name: '南昌市',
				val: '305'
			},
			{
				name: '赣州市',
				val: '306'
			},
			{
				name: '上饶市',
				val: '307'
			},
			{
				name: '吉安市',
				val: '308'
			},
			{
				name: '九江市',
				val: '309'
			},
			{
				name: '新余市',
				val: '310'
			},
			{
				name: '抚州市',
				val: '311'
			},
			{
				name: '宜春市',
				val: '312'
			},
			{
				name: '景德镇市',
				val: '313'
			},
			{
				name: '萍乡市',
				val: '314'
			},
			{
				name: '鹰潭市',
				val: '315'
			}
		]
	},
	{
		name:'湖北', 
		val : '332',
		cityList:
		[
			{
				name: '武汉市',
				val: '316'
			},
			{
				name: '宜昌市',
				val: '317'
			},
			{
				name: '襄樊市',
				val: '318'
			},
			{
				name: '荆州市',
				val: '319'
			},
			{
				name: '恩施州',
				val: '320'
			},
			{
				name: '黄冈市',
				val: '321'
			},
			{
				name: '孝感市',
				val: '322'
			},
			{
				name: '十堰市',
				val: '323'
			},
			{
				name: '咸宁市',
				val: '324'
			},
			{
				name: '黄石市',
				val: '325'
			},
			{
				name: '仙桃市',
				val: '326'
			},
			{
				name: '天门市',
				val: '327'
			},
			{
				name: '随州市',
				val: '328'
			},
			{
				name: '荆门市',
				val: '329'
			},
			{
				name: '潜江市',
				val: '330'
			},
			{
				name: '鄂州市',
				val: '331'
			},
			{
				name: '神农架林区',
				val: '332'
			}
		]
	},
	{
		name:'山西', 
		val : '343',
		cityList:
		[
			{
				name: '太原市',
				val: '333'
			},
			{
				name: '大同市',
				val: '334'
			},
			{
				name: '运城市',
				val: '335'
			},
			{
				name: '长治市',
				val: '336'
			},
			{
				name: '晋城市',
				val: '337'
			},
			{
				name: '忻州市',
				val: '338'
			},
			{
				name: '临汾市',
				val: '339'
			},
			{
				name: '吕梁市',
				val: '340'
			},
			{
				name: '晋中市',
				val: '341'
			},
			{
				name: '阳泉市',
				val: '342'
			},
			{
				name: '朔州市',
				val: '343'
			}
		]
	},
	{
		name:'辽宁', 
		val : '357',
		cityList:
		[
			{
				name: '大连市',
				val: '344'
			},
			{
				name: '沈阳市',
				val: '345'
			},
			{
				name: '丹东市',
				val: '346'
			},
			{
				name: '辽阳市',
				val: '347'
			},
			{
				name: '葫芦岛市',
				val: '348'
			},
			{
				name: '锦州市',
				val: '349'
			},
			{
				name: '朝阳市',
				val: '350'
			},
			{
				name: '营口市',
				val: '351'
			},
			{
				name: '鞍山市',
				val: '352'
			},
			{
				name: '抚顺市',
				val: '353'
			},
			{
				name: '阜新市',
				val: '354'
			},
			{
				name: '盘锦市',
				val: '355'
			},
			{
				name: '本溪市',
				val: '356'
			},
			{
				name: '铁岭市',
				val: '357'
			}
		]
	},
	{
		name:'台湾', 
		val : '364',
		cityList:
		[
			{
				name: '台北市',
				val: '358'
			},
			{
				name: '高雄市',
				val: '359'
			},
			{
				name: '台中市',
				val: '360'
			},
			{
				name: '新竹市',
				val: '361'
			},
			{
				name: '基隆市',
				val: '362'
			},
			{
				name: '台南市',
				val: '363'
			},
			{
				name: '嘉义市',
				val: '364'
			}
		]
	},
	{
		name:'黑龙江', 
		val : '377',
		cityList:
		[
			{
				name: '齐齐哈尔市',
				val: '365'
			},
			{
				name: '哈尔滨市',
				val: '366'
			},
			{
				name: '大庆市',
				val: '367'
			},
			{
				name: '佳木斯市',
				val: '368'
			},
			{
				name: '双鸭山市',
				val: '369'
			},
			{
				name: '牡丹江市',
				val: '370'
			},
			{
				name: '鸡西市',
				val: '371'
			},
			{
				name: '黑河市',
				val: '372'
			},
			{
				name: '绥化市',
				val: '373'
			},
			{
				name: '鹤岗市',
				val: '374'
			},
			{
				name: '伊春市',
				val: '375'
			},
			{
				name: '大兴安岭地区',
				val: '376'
			},
			{
				name: '七台河市',
				val: '377'
			}
		]
	},
	{
		name:'内蒙古', 
		val : '389',
		cityList:
		[
			{
				name: '赤峰市',
				val: '378'
			},
			{
				name: '包头市',
				val: '379'
			},
			{
				name: '通辽市',
				val: '380'
			},
			{
				name: '呼和浩特市',
				val: '381'
			},
			{
				name: '鄂尔多斯市',
				val: '382'
			},
			{
				name: '乌海市',
				val: '383'
			},
			{
				name: '呼伦贝尔市',
				val: '384'
			},
			{
				name: '兴安盟',
				val: '385'
			},
			{
				name: '巴彦淖尔盟',
				val: '386'
			},
			{
				name: '乌兰察布盟',
				val: '387'
			},
			{
				name: '锡林郭勒盟',
				val: '388'
			},
			{
				name: '阿拉善盟',
				val: '389'
			}
		]
	},
	{
		name:'澳门', 
		val : '390',
		cityList:
		[
			{
				name: '澳门特别行政区',
				val: '390'
			}
		]
	},
	{
		name:'贵州', 
		val : '399',
		cityList:
		[
			{
				name: '贵阳市',
				val: '391'
			},
			{
				name: '黔东南州',
				val: '392'
			},
			{
				name: '黔南州',
				val: '393'
			},
			{
				name: '遵义市',
				val: '394'
			},
			{
				name: '黔西南州',
				val: '395'
			},
			{
				name: '毕节地区',
				val: '396'
			},
			{
				name: '铜仁地区',
				val: '397'
			},
			{
				name: '安顺市',
				val: '398'
			},
			{
				name: '六盘水市',
				val: '399'
			}
		]
	},
	{
		name:'甘肃', 
		val : '413',
		cityList:
		[
			{
				name: '兰州市',
				val: '400'
			},
			{
				name: '天水市',
				val: '401'
			},
			{
				name: '庆阳市',
				val: '402'
			},
			{
				name: '武威市',
				val: '403'
			},
			{
				name: '酒泉市',
				val: '404'
			},
			{
				name: '张掖市',
				val: '405'
			},
			{
				name: '陇南地区',
				val: '406'
			},
			{
				name: '白银市',
				val: '407'
			},
			{
				name: '定西地区',
				val: '408'
			},
			{
				name: '平凉市',
				val: '409'
			},
			{
				name: '嘉峪关市',
				val: '410'
			},
			{
				name: '临夏回族自治州',
				val: '411'
			},
			{
				name: '金昌市',
				val: '412'
			},
			{
				name: '甘南州',
				val: '413'
			}
		]
	},
	{
		name:'青海', 
		val : '420',
		cityList:
		[
			{
				name: '西宁市',
				val: '414'
			},
			{
				name: '海西州',
				val: '415'
			},
			{
				name: '海东地区',
				val: '416'
			},
			{
				name: '海北州',
				val: '417'
			},
			{
				name: '果洛州',
				val: '418'
			},
			{
				name: '玉树州',
				val: '419'
			},
			{
				name: '黄南藏族自治州',
				val: '420'
			}
		]
	},
	{
		name:'新疆', 
		val : '438',
		cityList:
		[
			{
				name: '乌鲁木齐市',
				val: '421'
			},
			{
				name: '伊犁州',
				val: '422'
			},
			{
				name: '昌吉州',
				val: '423'
			},
			{
				name: '石河子市',
				val: '424'
			},
			{
				name: '哈密地区',
				val: '425'
			},
			{
				name: '阿克苏地区',
				val: '426'
			},
			{
				name: '巴音郭楞州',
				val: '427'
			},
			{
				name: '喀什地区',
				val: '428'
			},
			{
				name: '塔城地区',
				val: '429'
			},
			{
				name: '克拉玛依市',
				val: '430'
			},
			{
				name: '和田地区',
				val: '431'
			},
			{
				name: '阿勒泰州',
				val: '432'
			},
			{
				name: '吐鲁番地区',
				val: '433'
			},
			{
				name: '阿拉尔市',
				val: '434'
			},
			{
				name: '博尔塔拉州',
				val: '435'
			},
			{
				name: '五家渠市',
				val: '436'
			},
			{
				name: '克孜勒苏州',
				val: '437'
			},
			{
				name: '图木舒克市',
				val: '438'
			}
		]
	},
	{
		name:'西藏', 
		val : '445',
		cityList:
		[
			{
				name: '拉萨市',
				val: '439'
			},
			{
				name: '山南地区',
				val: '440'
			},
			{
				name: '林芝地区',
				val: '441'
			},
			{
				name: '日喀则地区',
				val: '442'
			},
			{
				name: '阿里地区',
				val: '443'
			},
			{
				name: '昌都地区',
				val: '444'
			},
			{
				name: '那曲地区',
				val: '445'
			}
		]
	},
	{
		name:'吉林', 
		val : '454',
		cityList:
		[
			{
				name: '吉林市',
				val: '446'
			},
			{
				name: '长春市',
				val: '447'
			},
			{
				name: '白山市',
				val: '448'
			},
			{
				name: '延边州',
				val: '449'
			},
			{
				name: '白城市',
				val: '450'
			},
			{
				name: '松原市',
				val: '451'
			},
			{
				name: '辽源市',
				val: '452'
			},
			{
				name: '通化市',
				val: '453'
			},
			{
				name: '四平市',
				val: '454'
			}
		]
	},
	{
		name:'宁夏', 
		val : '459',
		cityList:
		[
			{
				name: '银川市',
				val: '455'
			},
			{
				name: '吴忠市',
				val: '456'
			},
			{
				name: '中卫市',
				val: '457'
			},
			{
				name: '石嘴山市',
				val: '458'
			},
			{
				name: '固原市',
				val: '459'
			}
		]
	},
];
