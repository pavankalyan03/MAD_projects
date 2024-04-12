import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText


def prepare_mail(email, receiver, message):
    msg = MIMEMultipart("alternative")
    msg["From"] = email
    msg["To"] = receiver
    msg["Subject"] = "Message from Android"
    # simple paragraph, feel free to edit
    html = f"<p>{message}</p>"
    text_part = MIMEText(message, "plain")
    html_part = MIMEText(html, "html")
    msg.attach(text_part)
    msg.attach(html_part)
    # after making the mail, convert back as string message
    return msg.as_string()


def sendmail(email, password, receiver, message, verbose=0):
    # manages a connection to an SMTP server
    # in our case it's for Microsoft365, Outlook, Hotmail, and live.com
    server = smtplib.SMTP(host="smtp.office365.com", port=587)
    # connect to the SMTP server as TLS mode ( for security )
    server.starttls()
    # login to the email account
    server.login(email, password)
    # send the actual message after preparation
    server.sendmail(email, receiver, prepare_mail(email, receiver, message))
    # terminates the session
    server.quit()
    if verbose:
        return True


def main(receiver,msg):
    EMAIL_ADDRESS = "starboy1432@outlook.com"
    EMAIL_PASSWORD = "6305524392Pk@"
    sendmail(EMAIL_ADDRESS, EMAIL_PASSWORD, receiver, msg)
