'''
Emails the report of test to various people in the email list
'''
import os
import importlib

from SendEmail import EmailSender
importlib.reload(EmailSender)

class EmailReport:
    # region Initialisation
    def __init__(self):
        self.__initialiseClasses()

    def __initialiseClasses(self):
        '''
        Initialises Email Sender Class Object
        :return: None
        '''
        self.__sender = EmailSender.EmailSender()

    # endregion

    # region Prerequisites
    def __gatherDataFromEnviron(self):
        '''
        Gathers the necessary data from the environment variables in Bitrise
        -> Credentials
        -> List of reciever Email IDs
        -> Build Info
        :return: None
        '''
        self.__gatherCredentials()
        self.__gatherEmailIds()
        self.__gatherBuildInfo()

    def __gatherEmailIds(self):
        '''
        Gets the list of email IDs to send email to
        :return: None
        '''
        self.__emails = [str(eachId).strip() for eachId in
                         str(os.environ['EMAIL_IDS_LIST']).split(',')]

    def __gatherCredentials(self):
        '''
        Gathers Credentials used to send out emails
        :return: None
        '''
        self.__emailId = str(os.environ['REPORT_SENDER_EMAIL'])
        self.__emailPwd = str(os.environ['REPORT_SENDER_PWD'])

    def __gatherBuildInfo(self):
        '''
        Gathers build info by getting the Build Slug for the Bitrise Build
        :return: None
        '''
        self.__buildSlug = str(os.environ['BITRISE_BUILD_URL']).split('/')[-1].strip()

    def __buildSubjectLine(self):
        '''
        Builds the subject line for the email
        :return: None
        '''
        self.__subjectLine = 'PlantTester Test Report for Build ' + self.__buildSlug
    # endregion

    def sendReports(self, emailBody):
        '''
        Gathers necessary data from Environment, Builds subject line and
        uses EmailSender to send out the emails to all people in the email list
        :param emailBody: Dictionary of body of the mail, keyed to 'html' and 'text' blocks
        :return: None
        '''
        self.__gatherDataFromEnviron()
        self.__buildSubjectLine()
        self.__sender.sendEmails(self.__emailId, self.__emailPwd,
                                 subject=self.__subjectLine, emailList=self.__emails,
                                 body=emailBody)

