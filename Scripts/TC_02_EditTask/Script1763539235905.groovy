import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testdata.TestData as TestData
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

TestData data = findTestData('Data Files/EditTaskData')

// Pastikan dashboard tampil
Mobile.waitForElementPresent(findTestObject('Dashboard page/Todo_header_txt'), 10)

Mobile.delay(2)

for (int i = 1; i <= data.getRowNumbers(); i++) {
    String newTitle = data.getValue('NewTitle', i)

    String newDesc = data.getValue('NewDescription', i)

    String newDate = data.getValue('NewDate', i)

    String newTime = data.getValue('NewTime', i)

    String newCategory = data.getValue('NewCategory', i)

    Mobile.tap(findTestObject('Dashboard page/action_card_btn'), 10)

    // --- Tap card pertama ---
    //Mobile.verifyElementVisible(findTestObject('Dashboard page/cekDesc_card'), 10)
    //Mobile.tap(findTestObject('Dashboard page/cekDesc_card'), 10)
    // --- Tap tombol Edit ---
    Mobile.tap(findTestObject('Dashboard Page/Edit_btn'), 10)

    // ========== EDIT TITLE ==========
    if ((newTitle != null) && (newTitle.trim().length() > 0)) {
        Mobile.clearText(findTestObject('Add Task Page/TaskTitle_textfield'), 0)

        Mobile.setText(findTestObject('Add Task Page/TaskTitle_textfield'), newTitle, 5)
    } else {
        KeywordUtil.markWarning('NewTitle kosong → skip edit title')
    }
    
    // ========== EDIT DESCRIPTION ==========
    if ((newDesc != null) && (newDesc.trim().length() > 0)) {
        Mobile.clearText(findTestObject('Add Task Page/Description_textfield'), 0)

        Mobile.setText(findTestObject('Add Task Page/Description_textfield'), newDesc, 5)
    } else {
        KeywordUtil.markWarning('NewDescription kosong → skip edit description')
    }
    
    // ========== EDIT DATE ==========
    if ((newDate != null) && (newDate.trim().length() > 0)) {
        Mobile.tap(findTestObject('Add Task Page/SetDate_input'), 0)

        switch (newDate.toLowerCase()) {
            case '21nov':
                Mobile.tap(findTestObject('Add Task Page/SetDate_21nov_choose'), 0)

                break
            default:
                KeywordUtil.markWarning('Tanggal tidak cocok → skip')}
        
        Mobile.tap(findTestObject('Add Task Page/OKE_setdate_btn'), 0)
    }
    
    // ========== EDIT TIME ==========
    if ((newTime != null) && (newTime.trim().length() > 0)) {
        Mobile.tap(findTestObject('Edit Task Page/SetTime_input'), 0)

        switch (newTime.toLowerCase()) {
            case '4am':
                Mobile.tap(findTestObject('Add Task Page/SetTime_4am'), 0)

                break
            case '0055pm':
                Mobile.tap(findTestObject('Add Task Page/SetTime_0055pm'), 0)

                break
            default:
                KeywordUtil.markWarning('Waktu tidak cocok → pilih default 4am')

                Mobile.tap(findTestObject('Add Task Page/SetTime_4am'), 0)}
        
        Mobile.tap(findTestObject('Add Task Page/OKE_settime_btn'), 0)
    }
    
    // ========== EDIT CATEGORY ==========
    if ((newCategory != null) && (newCategory.trim().length() > 0)) {
        Mobile.tap(findTestObject('Add Task Page/addToCategory_dropdown'), 0)

        switch (newCategory.toLowerCase()) {
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
                KeywordUtil.markWarning('Category tidak cocok → pilih Personal')

                Mobile.tap(findTestObject('Add Task Page/Personal_category'), 0)}
    }
    
    // SIMPAN PERUBAHAN
    Mobile.tap(findTestObject('Edit Task Page/Save_change_btn'), 0)

    // Kembali ke dashboard
    Mobile.waitForElementPresent(findTestObject('Dashboard page/Todo_header_txt'), 10)

    Mobile.delay(1)
}

Mobile.checkElement(findTestObject(null), 0)

