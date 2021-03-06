'''
Gathers report data from a given logcat
'''
# region Imports
import os
import importlib
from CreateReport import TestCases
importlib.reload(TestCases)
# endregion

class GatherLogCatData:
    # region Initialisation
    def __init__(self):
        self.__initialiseVariables()
        self.__loadTestCaseData()

    def __initialiseVariables(self, loadingClass=True):
        '''
        Initialise base variables of the class
        :param loadingClass: Whether the class is being loaded (True/False)
        :return: None
        '''
        self.__filePath = ''
        self.__logTag = 'PlantTester_UITest'
        self.__steps = []
        self.__reportData = {}
        self.__reportTitle = ''
        if loadingClass:
            self.__testCases = {}

    def __loadTestCaseData(self):
        '''
        Get the test case dictionary
        :return: None
        '''
        self.__testCases = TestCases.TEST_CASES
    # endregion

    # region Helper Methods
    def __readLogCat(self):
        '''
        Read the given logcat and find the steps of the UI test
        :return: None
        '''
        fPtr = open(self.__filePath, 'r')
        for eachLine in fPtr.readlines():
            if self.__logTag in eachLine:
                eachStep = eachLine.split(':')[-1].strip()
                self.__steps.append(eachStep)
        fPtr.close()

    def __getReportTitle(self):
        '''
        Get the Report title based on the name of the logcat
        :return:
        '''
        for eachKey in self.__testCases.keys():
            if str(eachKey) in os.path.basename(self.__filePath):
                self.__reportTitle = self.__testCases[eachKey]
                break
    # endregion

    def getReportData(self, filePath):
        '''
        Initialises base variables again, save for the ones done during creation of
        class object, gets the report title, reads the Logcat for UI test steps and
        returns a Test Title and Steps in the Test
        :param filePath: Path of the logcat file
        :return: Test Title String, List of Step strings for Test Case
        '''
        self.__initialiseVariables(loadingClass=False)
        self.__filePath = filePath
        self.__getReportTitle()
        self.__readLogCat()

        return self.__reportTitle, self.__steps

