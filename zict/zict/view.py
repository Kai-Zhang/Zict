# -*- coding: utf-8 -*-
__author__ = 'Zhao Guoyan'

from django.shortcuts import render
from zict.client import tcpManage


def home(request):
    tcpManage.connectServer()
    return render(request, 'home.html')


def login_view(request):
    return render(request,'login.html')

def register_view(request):
    return render(request,'register.html')

def toBeContinued(request):
    return render(request,'toBeContinued.html')
