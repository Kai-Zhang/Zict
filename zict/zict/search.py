# -*- coding: utf-8 -*-

from django.http import HttpResponse
from django.shortcuts import render_to_response
from django.shortcuts import render
from zict.client import *
import json

# 接收请求数据
def search(request):
    request.encoding = 'utf-8'
    print request.GET['word'].encode('utf-8')
    get_data = tcpManage.wordQuery(request.GET['word'].encode('utf-8'))
    baidu_data=get_data['baidu'][0]
    youdao_data=get_data['youdao'][0]
    bing_data=get_data['bing'][0]
    baidu_zan=get_data['baidu'][1]
    youdao_zan=get_data['youdao'][1]
    bing_zan=get_data['bing'][1]
    baidu_zan_me=get_data['baidu'][2]
    youdao_zan_me=get_data['youdao'][2]
    bing_zan_me=get_data['bing'][2]

    tempLogin=userConfig.hadLogin
    print tempLogin
    tempUsername=userConfig.UserName
    return render(request, 'search_result.html', locals())

