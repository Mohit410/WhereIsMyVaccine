# ![vaccine](https://github.com/Mohit410/WhereIsMyVaccine/blob/master/app/src/main/res/drawable/ic_vaccine.png)   WhereIsMyVaccine - A Vaccine Tracker App
<h1>Overview</h1>
<p>WIMV is a vaccine tracker android app which displays the details of vaccination centers with vaccine availibility. In this project, I have used volley library to get api calls and responses. It ask user for their area pincode and pick the date on which user wants to check vaccine availability and return data like vaccination center name, address, vaccine name, vaccine fee type, available vaccines, and timings.</p>

<h1>Project Structure</h1>
<p>This app contains only a single package</p>
<h5>com.mohitsharda.whereismyvaccine(package)</h5>
  <ul>
    <li>CenterRVAdapter.kt (for creating RecyclerView)</li>
    <li>CenterRvModel.kt (Model class for storing information for RecyclerView)</li>
    <li>MainActivity.kt (Starting the app and creating the view)</li>
  </ul>
  
<h1>API Used</h1>
<p>Thanks to <a href="https://apisetu.gov.in">APISetu</a> website for such a good API collection.</p>
```
https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=$pinCode&date=$date
```

<h1>Screenshots</h1>
<p> ![appicon] (/WIMV\ Screenshots/Screenshot_20210523-211207_Samsung Experience Home.png)</p>
