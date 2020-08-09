'''
Gets the Data from Test Results XML file
'''

import xml.etree.cElementTree as ET

class GatherResultsData:
    # region Initialisation
    def __init__(self):
        self.__initialiseVariables()

    def __initialiseVariables(self):
        self.testData = {}
    # endregion

    # region Helper Methods
    def __getTestSuiteData(self, elem):
        '''
        Gets the relevant items from testsuite tag
        :param elem: testsuite xml element
        :return: None
        '''
        self.testData['Total Tests'] = str(elem.get('tests'))
        self.testData['Failures'] = str(elem.get('failures'))
        self.testData['Errors'] = str(elem.get('errors'))
        self.testData['Skipped'] = str(elem.get('skipped'))
        self.testData['Total Time'] = str(elem.get('time'))
    # endregion

    def getResultsXmlData(self, xmlPath):
        '''
        Iterates over XML Tree to find testsuite tag and get relevant data from it
        :param xmlPath: File Path of xml
        :return: Test Suite Data (Dictionary)
        '''
        self.__initialiseVariables()

        tree = ET.ElementTree(file=xmlPath)
        for element in tree.iter():
            if element.tag == 'testsuite':
                self.__getTestSuiteData(element)

        return self.testData






