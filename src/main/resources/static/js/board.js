
"use strict";

let size = 10;
let sort = "auditLogLogId";
let direction = "Sort.Direction.ASC";
let isAsc = "ASC";


function pagination(page) {
    let count = page -1 ;

    console.log(count);
    // const xhr = new XMLHttpRequest();
    // const method ="GET";
    let url;
    if (!searchKeyword) {
        url = "/board/list"+"?"+"page="+count+"&size="+size+"&sort="+sort+"&direction="+direction+"&isAsc="+isAsc;
    } else {
        url = "/board/list"+"?"+"page="+count+"&size="+size+"&sort="+sort+"&direction="+direction+"&isAsc="+isAsc+"&searchKeyword="+searchKeyword;
    }


    // console.log(url)
    location.href = url;
    // xhr.open(method, url);
    // xhr.send();
}

function search() {
    let element= document.getElementById('searchInput');

    searchKeyword = element.value;

    let url;
    if (!searchKeyword) {
        url = "/board/list"+"?"+"&size="+size+"&sort="+sort+"&direction="+direction+"&isAsc="+isAsc;
    } else {
        url = "/board/list"+"?"+"&size="+size+"&sort="+sort+"&direction="+direction+"&isAsc="+isAsc+"&searchKeyword="+searchKeyword;
    }
    location.href = url;
}