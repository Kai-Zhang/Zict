from django.conf.urls import patterns, include, url
from django.contrib import admin
from zict.view import *
from zict import search
from zict.client import *

admin.autodiscover()

urlpatterns = patterns('',
                       # Examples:

                       ('^$',home ),
                       # ('^home/$',home_view ),
                       (r'^search/$', search.search),
                       url(r'^login/$', login_view),
                       url(r'^user_login/$', user_login),
                       url(r'^register/$', register_view),
                       url(r'^user_register/$', user_register),
                       url(r'^logout/$', logout),
                       url(r'^baidu_zan/$', user_clickLike_baidu),
                       url(r'^toBeContinued/$', toBeContinued),
                       url(r'^admin/', include(admin.site.urls)),
                       )
