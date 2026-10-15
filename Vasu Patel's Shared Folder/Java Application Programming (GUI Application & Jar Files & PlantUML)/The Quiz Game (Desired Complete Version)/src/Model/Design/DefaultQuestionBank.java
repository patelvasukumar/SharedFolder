package Model.Design;

import java.util.Hashtable;
import util.LanguageManager;

public class DefaultQuestionBank {

    public static Hashtable<Integer, Question> loadQuestions() {

        Hashtable<Integer, Question> questions = new Hashtable<>();

        long[] price = {
                100,200,300,500,1000,2000,4000,8000,
                16000,32000,64000,125000,250000,500000,1000000
        };

        boolean eng = LanguageManager.isEnglish();

        if (eng) {
            questions.put(1,new Question(
                    1,
                    "What is the capital of India?",
                    "What is the capital of Canada?",
                    price[0],
                    "New Delhi","Surat","Mumbai","Chennai",
                    "Ottawa","Toronto","Vancouver","Montreal"
            ));

            questions.put(2,new Question(
                    2,
                    "Which festival is known as the Festival of Lights in India?",
                    "Which leaf appears on the Canadian national flag?",
                    price[1],
                    "Diwali","Holi","Navratri","Eid",
                    "Maple Leaf","Oak Leaf","Pine Leaf","Palm Leaf"
            ));

            questions.put(3,new Question(
                    3,
                    "Which is the largest democracy in the world?",
                    "Which province has the largest population in Canada?",
                    price[2],
                    "India","USA","Brazil","Indonesia",
                    "Ontario","Alberta","Quebec","Manitoba"
            ));

            questions.put(4,new Question(
                    4,
                    "In which year did India gain independence?",
                    "In which year was Canada formed as a confederation?",
                    price[3],
                    "1947","1935","1950","1962",
                    "1867","1914","1901","1776"
            ));

            questions.put(5,new Question(
                    5,
                    "Which Indian mission successfully landed near the Moon's south pole in 2023?",
                    "Which organization is Canada part of for military alliance?",
                    price[4],
                    "Chandrayaan-3","Mangalyaan","Chandrayaan-2","Aditya-L1",
                    "NATO","BRICS","OPEC","ASEAN"
            ));

            questions.put(6,new Question(
                    6,
                    "Which country did India fight in the 1999 Kargil conflict?",
                    "Which Canadian city hosted the 1976 Summer Olympics?",
                    price[5],
                    "Pakistan","China","Nepal","Sri Lanka",
                    "Montreal","Toronto","Ottawa","Vancouver"
            ));

            questions.put(7,new Question(
                    7,
                    "Which Indian operation pushed back infiltrators during the Kargil War?",
                    "Which country shares the longest land border with Canada?",
                    price[6],
                    "Operation Vijay","Operation Meghdoot","Operation Trident","Operation Blue Star",
                    "United States","Russia","Mexico","Brazil"
            ));

            questions.put(8,new Question(
                    8,
                    "Which organization manages international peacekeeping missions?",
                    "Which province in Canada is primarily French-speaking?",
                    price[7],
                    "United Nations","NATO","G20","OPEC",
                    "Quebec","Ontario","Alberta","Manitoba"
            ));

            questions.put(9,new Question(
                    9,
                    "Which country fought a war with India in 1962?",
                    "Which is the longest river in Canada?",
                    price[8],
                    "China","Pakistan","Nepal","Bangladesh",
                    "Mackenzie River","Fraser River","Yukon River","St. Lawrence River"
            ));

            questions.put(10,new Question(
                    10,
                    "Which political party is considered the largest political party in the world by membership?",
                    "Which Canadian province is the largest by area?",
                    price[9],
                    "Bharatiya Janata Party","Democratic Party","Republican Party","Labour Party",
                    "Quebec","Ontario","Alberta","British Columbia"
            ));

            questions.put(11,new Question(
                    11,
                    "Which country controls most of the Siachen Glacier region today?",
                    "Which global alliance includes Canada and the United States?",
                    price[10],
                    "India","Pakistan","China","Nepal",
                    "NATO","BRICS","ASEAN","SAARC"
            ));

            questions.put(12,new Question(
                    12,
                    "Which agreement was signed after the 1971 India-Pakistan war?",
                    "Which Canadian city is considered Canada's financial center?",
                    price[11],
                    "Simla Agreement","Tashkent Agreement","Delhi Treaty","Indus Treaty",
                    "Toronto","Ottawa","Vancouver","Calgary"
            ));

            questions.put(13,new Question(
                    13,
                    "Which Indian city is known as the financial capital of India?",
                    "Which country has the second largest land area in the world after Russia?",
                    price[12],
                    "Mumbai","Delhi","Chennai","Kolkata",
                    "Canada","USA","China","Brazil"
            ));

            questions.put(14,new Question(
                    14,
                    "Which movement historically demanded a separate Sikh state called Khalistan?",
                    "Which ocean lies east of Canada?",
                    price[13],
                    "Khalistan movement","Chipko Movement","Green Revolution","Quit India Movement",
                    "Atlantic Ocean","Pacific Ocean","Indian Ocean","Arctic Ocean"
            ));

            questions.put(15,new Question(
                    15,
                    "Which global conflict lasted from 1939 to 1945?",
                    "Which country is the largest democracy in the world?",
                    price[14],
                    "World War II","World War I","Cold War","Korean War",
                    "India","USA","Brazil","Indonesia"
            ));
        } else {
            // Hindi questions (simple translations)
            questions.put(1,new Question(
                    1,
                    "भारत की राजधानी क्या है?",
                    "कनाडा की राजधानी क्या है?",
                    price[0],
                    "नई दिल्ली","सूरत","मुंबई","चेन्नई",
                    "ओटावा","टोरंटो","वैंकूवर","मॉन्ट्रियल"
            ));

            questions.put(2,new Question(
                    2,
                    "भारत में किस त्योहार को रोशनी का त्योहार कहा जाता है?",
                    "कनाडाई राष्ट्रीय झंडे पर कौन-सा पत्ता दिखाई देता है?",
                    price[1],
                    "दिवाली","होली","नवरात्रि","ईद",
                    "मैपल लीफ़","ओक लीफ़","पाइन लीफ़","पाम लीफ़"
            ));

            questions.put(3,new Question(
                    3,
                    "दुनिया का सबसे बड़ा लोकतंत्र कौन-सा है?",
                    "कनाडा में किस प्रांत की आबादी सबसे ज्यादा है?",
                    price[2],
                    "भारत","अमेरिका","ब्राज़िल","इंडोनेशिया",
                    "ओंटेरियो","अल्बर्टा","क्यूबेक","मैनिटोबा"
            ));

            questions.put(4,new Question(
                    4,
                    "भारत को आज़ादी किस वर्ष मिली?",
                    "कनाडा कब कॉन्फेडरेशन के रूप में बना?",
                    price[3],
                    "1947","1935","1950","1962",
                    "1867","1914","1901","1776"
            ));

            questions.put(5,new Question(
                    5,
                    "किस भारतीय मिशन ने 2023 में चंद्रमा के साउथ पोल के पास लैंडिंग की?",
                    "कनाडा किस सैन्य संगठन का हिस्सा है?",
                    price[4],
                    "चंद्रयान-3","मंगलयान","चंद्रयान-2","आदित्य-एल1",
                    "NATO","BRICS","OPEC","ASEAN"
            ));

            questions.put(6,new Question(
                    6,
                    6 == 6 ? "1999 के कारगिल संघर्ष में भारत किस देश से लड़ा?" : "",
                    "1976 के समर ओलिंपिक्स की मेज़बान कनाडाई शहर कौन सा था?",
                    price[5],
                    "पाकिस्तान","चीन","नेपाल","श्रीलंका",
                    "मॉन्ट्रियल","टोरंटो","ओटावा","वैंकूवर"
            ));

            questions.put(7,new Question(
                    7,
                    "कारगिल युद्ध में कौन सा ऑपरेशन शामिल था जिसने घुसपैठियों को पीछे हटाया?",
                    "कनाडा का सबसे लंबा भूमि सीमा वाला कौन सा देश है?",
                    price[6],
                    "ऑपरेशन विजय","ऑपरेशन मेघदूत","ऑपरेशन ट्राइडेंट","ऑपरेशन ब्लू स्टार",
                    "संयुक्त राज्य","रूस","मैक्सिको","ब्राज़ील"
            ));

            questions.put(8,new Question(
                    8,
                    "अंतरराष्ट्रीय शांति अभियानों का प्रबंधन कौन सा संगठन करता है?",
                    "कनाडा का कौन सा प्रांत मुख्य रूप से फ्रेंच बोलने वाला है?",
                    price[7],
                    "संयुक्त राष्ट्र","NATO","G20","OPEC",
                    "क्यूबेक","ओंटेरियो","अल्बर्टा","मैनिटोबा"
            ));

            questions.put(9,new Question(
                    9,
                    "1962 में भारत ने किस देश के साथ युद्ध किया था?",
                    "कनाडा की सबसे लंबी नदी कौन सी है?",
                    price[8],
                    "चीन","पाकिस्तान","नेपाल","बांग्लादेश",
                    "मैकेंज़ी नदी","फ्रेज़र नदी","युकॉन नदी","सेंट लॉरेन्स नदी"
            ));

            questions.put(10,new Question(
                    10,
                    "सदस्यता के अनुसार दुनिया की सबसे बड़ी राजनीतिक पार्टी कौन सी मानी जाती है?",
                    "कनाडा का सबसे बड़ा क्षेत्रफल वाला प्रांत कौन सा है?",
                    price[9],
                    "भारतीय जनता पार्टी","डेमोक्रेटिक पार्टी","रिपब्लिकन पार्टी","लेबर पार्टी",
                    "क्यूबेक","ओंटेरियो","अल्बर्टा","ब्रिटिश कोलंबिया"
            ));

            questions.put(11,new Question(
                    11,
                    "आज कौन सा देश सियाचिन ग्लेशियर के अधिकतर हिस्से पर नियंत्रण करता है?",
                    "कनाडा और संयुक्त राज्य अमेरिका किस वैश्विक गठबंधन के सदस्य हैं?",
                    price[10],
                    "भारत","पाकिस्तान","चीन","नेपाल",
                    "NATO","BRICS","ASEAN","SAARC"
            ));

            questions.put(12,new Question(
                    12,
                    "1971 के भारत-पाकिस्तान युद्ध के बाद कौन सा समझौता हुआ?",
                    "कनाडा का वित्तीय केंद्र किस शहर को माना जाता है?",
                    price[11],
                    "शिमला समझौता","ताशकंद समझौता","दिल्ली संधि","इंडस संधि",
                    "टोरंटो","ओटावा","वैंकूवर","कैलगरी"
            ));

            questions.put(13,new Question(
                    13,
                    "भारत का वित्तीय राजधानी कौन सा शहर है?",
                    "रूस के बाद किस देश का क्षेत्रफल दूसरा सबसे बड़ा है?",
                    price[12],
                    "मुंबई","दिल्ली","चेन्नई","कोलकाता",
                    "कनाडा","यूएसए","चीन","ब्राज़ील"
            ));

            questions.put(14,new Question(
                    14,
                    "किस आंदोलन ने ऐतिहासिक रूप से अलग सिख राज्य 'खालिस्तान' की मांग की?",
                    "कनाडा के पूर्व में कौन सा महासागर है?",
                    price[13],
                    "खालिस्तान आंदोलन","चिपको आंदोलन","हरी क्रांति","क्विट इंडिया आंदोलन",
                    "अटलांटिक महासागर","प्रशांत महासागर","हिंद महासागर","आर्कटिक महासागर"
            ));

            questions.put(15,new Question(
                    15,
                    "1939 से 1945 तक कौन सा वैश्विक संघर्ष चला?",
                    "दुनिया का सबसे बड़ा लोकतंत्र कौन सा है?",
                    price[14],
                    "द्वितीय विश्व युद्ध","प्रथम विश्व युद्ध","शीत युद्ध","कोरियाई युद्ध",
                    "भारत","यूएसए","ब्राज़ील","इंडोनेशिया"
            ));
        }

        return questions;
    }
}