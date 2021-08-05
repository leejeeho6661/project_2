# -*- coding: utf-8 -*-

import requests as rq   #파이썬에서 웹사이트에 접속할때 사용하는 오픈소스(크롤링)
from bs4 import BeautifulSoup as bs #크롤링해온것을 html소스화 시키는 오픈소스
from urllib.request import urlretrieve as urlr  #이미지 다운받을때 사용  
import cx_Oracle as cx      #오라클 접속
import datetime
import os
print("========== -1")

raw = rq.get("https://www.yna.co.kr/theme/mostviewed/index",headers={"User-Agent":"Mozilla/5.0"})

html = bs(raw.text,"lxml") #파싱(html.parser/lxml 등)

li = html.select("ul.list li div.news-con") #li -> list

cnt = 1;    #count변수(10개해주려고)
print("============== 0")
#imgPath = "../../webapp/resources/article_img/" #이미지 저장경로
# 경로 error !! 
  
#imgPath = 'D:/LJH_student/works/egov/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/project/resources/article_img/'
imgPath = 'D:/LJH_student/works/egov/project/src/main/webapp/resources/article_img/'
con = cx.connect("java/java@localhost:1521/xe")

 
cur = con.cursor()
cur.execute("select BOARDWRITEPICTURE from article where boardWriteCategory='mostview'")
for ar in cur:
     if os.path.isfile(imgPath+str(ar)[2:len(str(ar))-3]):
        os.remove(imgPath+str(ar)[2:len(str(ar))-3])
 
cur.execute("delete article where boardWriteCategory='mostview'")
con.commit()
print("==================== 1")

for news in li:
    
    url = "https:"+news.select_one("a.tit-wrap")["href"] #기사 디테일 사이트
     
    eachraw = rq.get(url,headers={"User-Agent":"Mozilla/5.0"}) #해당 사이트의 html코드 받아오기
    eachhtml = bs(eachraw.text,"lxml")  #파싱
    articles = eachhtml.select("div.article") #기사영역
     
    article = ""    #기사 내용 외의 것들을 걸러내서 저장시켜줄 변수
    writer = ""     #기자
    for arti in articles:
        for ar in arti.find_all('p',class_=''):
            if(ar.text.__contains__("yna.co.kr")):
                continue
            elif(ar.text.__contains__("---")):
                break
            elif(ar.text.__contains__("기자")):
                writer = ar.text.strip()[ar.text.strip().find("기자")-4:ar.text.strip().find("기자")+2]
            else:
                article +=str(ar)
        if(arti.select_one("strong.tit-name") is not None):
            writer = arti.select_one("strong.tit-name").text
    #article = article.replace("'",'"')
    
    title = news.select_one("strong.tit-news").text
    time = news.select_one("span.txt-time").text.replace("-","/")+":00"
    print("================= 2")
    print("title : "+title)
    
    inserttime = datetime.datetime.strptime(time, '%Y/%m/%d %H:%M:%S')
    img = "http:"+arti.select_one("span.img img").attrs["src"]
    imgfile = str(img[46:].strip())
    urlr(img,imgPath+imgfile)
    
#     print("article = "+article)
#     print("url = "+url)
#     print("writer = "+writer)
#     print("title = "+title)
#     print("time = "+time)
#     print("img = "+imgfile)
    cnt=cnt+1
    #카테고리, 타이틀, 픽쳐, 롸이터, 아티클, 좋아요, 조회수, 올린날짜
    db_list = [("mostview",title,imgfile,writer,article,0,0,inserttime)]
    cur = con.cursor()
    cur.bindarraysize=1
    cur.executemany("insert into article values(:1, :2, :3, :4, :5, :6, :7, :8)",db_list)
    con.commit() 
    if cnt==11:
        break

print("======= 4 break")

cur = con.cursor()
cur.execute("select * from article")
for ar in cur:
    print(ar)
cur.close()
con.close()
