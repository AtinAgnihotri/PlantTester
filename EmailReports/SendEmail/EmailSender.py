'''
Sends email to multiple people
'''
import smtplib
import ssl
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

class EmailSender:
    # region Initialisation
    def __init__(self):
        pass
    # endregion

    # region Helper Methods
    def __sendSMTPEmails(self, message, emailID, emailPwd, emailList):
        '''
        Iterates over the email list and sends them the report email
        :param message: Message to be sent
        :param emailID: Sender Email ID
        :param emailPwd: Sender Email Password
        :param emailList: List of Reciever Email IDs
        :return: None
        '''
        context = ssl.create_default_context()
        with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
            server.login(emailID, emailPwd)
            for eachId in emailList:
                message['To'] = eachId
                server.sendmail(emailID, eachId, message.as_string())

    def __createMessage(self, subject, emailID, body):
        '''
        Creates the message to be sent to reciever email ids
        :param subject: Subject line of Email
        :param emailID: Sender Email ID
        :param body: Dictionary of body of email, keyed to 'html' and 'text' part
        :return:
        '''
        message = MIMEMultipart('alternative')
        message['Subject'] = subject
        message['From'] = emailID
        # message['To'] to be changed for every email in the list

        textStr = body['text']
        htmlStr = body['html']

        plainTextPart = MIMEText(textStr, 'plain')
        htmlPart = MIMEText(htmlStr, 'html')

        message.attach(plainTextPart)
        message.attach(htmlPart)

        return message
    # endregion

    def sendEmails(self, emailID, emailPwd, subject='', emailList=None,
                   body=None):
        '''
        Send the emails of test report to the email list ids
        :param emailID: Sender Email ID
        :param emailPwd: Sender Email Password
        :param subject: Subject line of the mail (Default : Empty String)
        :param emailList: List of reciever email ids (Default : Empty List)
        :param body: Dictionary of body of email, keyed to 'html' and 'text' blocks
            (Default : Empty Dictionary)
        :return: None
        '''
        if body is None:
            body = {}
        if emailList is None:
            emailList = []
        message = self.__createMessage(subject, emailID, body)
        self.__sendSMTPEmails(message, emailID, emailPwd, emailList)


