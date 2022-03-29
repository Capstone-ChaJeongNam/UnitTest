# Mpchart_3.1.0
MpAndroidChart 라이브러리 v3.1.0 예제

## demo
<img src="https://user-images.githubusercontent.com/47443884/160621859-1de4339a-cd3a-4b09-979d-ae3e8b94a063.png" width="800px"><br>

##Custom

```Java
        /**
        * 그래프에 넣을 데이트 리스트 
        */
        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 3));
        entries.add(new Entry(4, 4));
        entries.add(new Entry(5, 1));
        /**
        * 데이터 적용 및 Label 설정
        */
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        
        /**
        * 데이터 속성 설정 
        * setDrawValues - 값 표기 할지 말지 설정
        */
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK); // styling, ...
        dataSet.setDrawValues(false);
        /**
        * 데이터 속성 적용
        * 
        */
        LineData lineData = new LineData(dataSet);
        
        chart.setData(lineData);
        chart.setDescription(null);
        
        /**
        * Y축 관련 설정
        * 각 축값이 0, 1, 2, 3, 4로 떨어지도록 설정
        * 축의 각 값이 y 리스트의 value로 설정
        */
        YAxis left = chart.getAxisLeft();
        left.setDrawZeroLine(true);
        left.setLabelCount(5,true);
        left.setAxisMinimum(0);
        left.setAxisMaximum(4);
        
        List<String> y = new ArrayList<>();
        y.add("0");
        y.add("1");
        y.add("2");
        y.add("3");
        y.add("C");
        left.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return y.get((int)value);
            }
        });
        /**
        * X축 관련 설정
        *
        * x.setPosition(XAxis.XAxisPosition.BOTTOM) - 밑에 선 그려지도록
        *         
        * 각 축값이 0, 1, 2, 3, 4, 5로 떨어지도록 설정
        * x.setAxisMinimum(0f);
        * x.setAxisMaximum(5f);  
        * x.setLabelCount(6, true);
        * x.setGridLineWidth(1f); - 구분 선 간격 설정
        */
       
        XAxis x = chart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
//        x.setDrawGridLines(false);
        x.setAxisMinimum(0f);
        x.setAxisMaximum(5f);
        x.setLabelCount(6, true);
        x.setGridLineWidth(1f);



```
