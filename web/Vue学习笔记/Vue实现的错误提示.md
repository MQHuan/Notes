html代码
```
···
  <div id="errorMsg" class="alert alert-danger text-center" style="display:none;"></div>
···
```

js代码
```
···
$("#errorMsg").html(response.data.rspMsg);
$("#errorMsg").show();
···
```
