import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

// Panggil test case untuk buka aplikasi
// Ambil data
TestData data = findTestData('Data Files/AddTaskData')

for (int i = 1; i <= data.getRowNumbers(); i++) {
    String taskTitle = data.getValue('TaskTitle', i)

    String desc = data.getValue('Description', i)

    Mobile.checkElement(findTestObject(null), 0)

    String setDate = data.getValue('SetDate', i)

    String setTime = data.getValue('SetTime', i)

    String category = data.getValue('Category', i)

    // Tap tombol Add
    Mobile.tap(findTestObject('Dashboard page/AddTask_btn'), 0)

    Mobile.verifyElementVisible(findTestObject('Add Task Page/AddTask_header_txt'), 0)

    // Isi title & deskripsi
    Mobile.setText(findTestObject('Add Task Page/TaskTitle_textfield'), taskTitle, 5)

    Mobile.setText(findTestObject('Add Task Page/Description_textfield'), desc, 5)

    // IF-ELSE untuk setting date & time hanya jika data tidak kosong
    if ((setDate != null) && (setDate.trim().length() > 0)) {
        // Set date
        Mobile.tap(findTestObject('Add Task Page/SetDate_input'), 0)

        // Pilih tanggal sesuai data (contoh “21nov”)
        if (setDate.equalsIgnoreCase('21nov')) {
            Mobile.tap(findTestObject('Add Task Page/SetDate_21nov_choose'), 0)
        }
        
        // Klik OK tanggal
        Mobile.tap(findTestObject('Add Task Page/OKE_setdate_btn'), 0)
    } else {
        println('SetDate kosong → tidak memilih tanggal')
    }
    
    if ((setTime != null) && (setTime.trim().length() > 0)) {
        // Set time
        Mobile.tap(findTestObject('Add Task Page/SetTime_btn'), 0)

        // SWITCH CASE untuk waktu
        switch (setTime.toLowerCase()) {
            case '4am':
                Mobile.tap(findTestObject('Add Task Page/SetTime_4am'), 0)

                break
            case '0055pm':
                Mobile.tap(findTestObject('Add Task Page/setTime_0055pm'), 0)

                break
            // waktu default kalau tidak sesuai data
            default:
                Mobile.tap(findTestObject('Add Task Page/SetTime_4am'), 0)

                break
        }
        
        Mobile.tap(findTestObject('Add Task Page/OKE_settime_btn'), 0)
    } else {
        println('SetTime kosong → tidak memilih waktu')
    }
    
    // Pilih kategori dengan SWITCH CASE
    Mobile.tap(findTestObject('Add Task Page/addToCategory_dropdown'), 0)

    switch (category.toLowerCase()) {
        case 'personal':
            Mobile.tap(findTestObject('Add Task Page/Personal_category'), 0)

            break
        case 'banking':
            Mobile.tap(findTestObject('Add Task Page/Banking_category'), 0)

            break
        case 'business':
            Mobile.tap(findTestObject('Add Task Page/Business_category'), 0)

            break
        case 'insurance':
            Mobile.tap(findTestObject('Add Task Page/Insurance_category'), 0)

            break
        case 'shopping':
            Mobile.tap(findTestObject('Add Task Page/Shopping_category'), 0)

            break
        default:
            println('Category tidak dikenal → pilih Personal sebagai default')

            Mobile.tap(findTestObject('Add Task Page/Personal_category'), 0)

            break
    }
    
    // Klik Simpan
    Mobile.tap(findTestObject('Add Task Page/SaveTask_btn'), 0)

    // Verifikasi kembali ke halaman dashboard / list
    Mobile.verifyElementVisible(findTestObject('Dashboard page/Todo_header_txt'), 0)
}

