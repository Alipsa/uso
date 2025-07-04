# mail

The `mail` task sends SMTP email. It supports plain text, UU encoding, or MIME format mail, including attachments. SMTP authentication and SSL/TLS require JavaMail or JakartaMail (MIME only).

## Usage Examples

```groovy
target('sendSimpleMail') {
  mail(
    from: 'me@example.com',
    tolist: 'you@example.com',
    subject: 'Build Complete'
  ) {
    message(message: 'The nightly build has completed successfully.')
  }
}
```

```groovy
target('sendMailWithAttachment') {
  mail(
    mailhost: 'smtp.example.com',
    mailport: 587,
    from: 'ci@example.com',
    tolist: 'team@example.com',
    subject: 'Build Artifacts'
  ) {
    messagefile(srcFile: 'build/report.html')
    attachments {
      fileset(dir: 'build/artifacts', includes: '**/*.jar')
    }
  }
}
```

## Attributes

| Attribute                  | Description                                                                                          | Required        |
|---------------------------|------------------------------------------------------------------------------------------------------|----------------|
| `from`                     | Email address of sender (or nested `<from>` element)                                                 | One of          |
| `replyto`                  | Reply-to email address (or nested `<replyto>`)                                                       | No             |
| `tolist`                   | Comma-separated list of recipients (or nested `<to>`)                                                | One of          |
| `cclist`                   | Comma-separated list of CC recipients (or nested `<cc>`)                                             | No             |
| `bcclist`                  | Comma-separated list of BCC recipients (or nested `<bcc>`)                                           | No             |
| `subject`                  | Email subject line                                                                                  | No             |
| `message`                  | Message body text (or nested `<message>` element)                                                    | One of          |
| `messagefile`              | Path to file to send as message body                                                                | No             |
| `messagefileinputencoding` | Encoding of the input message file                                                                  | No             |
| `messagemimetype`          | Content type of the message (default: `text/plain`)                                                  | No             |
| `files`                    | Comma- or space-separated list of files to attach (or nested `<attachments>` with `<fileset>`)       | No             |
| `failonerror`              | Halt build on error (default: `true`)                                                                | No             |
| `includefilenames`         | Include filenames before attachment contents (default: `false`, plain encoding only)                 | No             |
| `mailhost`                 | SMTP server hostname (default: `localhost`)                                                          | No             |
| `mailport`                 | SMTP server TCP port (default: `25`)                                                                 | No             |
| `user`                     | Username for SMTP authentication (requires JavaMail, MIME only)                                       | No             |
| `password`                 | Password for SMTP authentication (requires JavaMail, MIME only)                                       | No             |
| `ssl`                      | Enable SSL/TLS (`true`/`false`, MIME only)                                                          | No             |
| `encoding`                 | Email content encoding: `mime`, `uu`, `plain`, `auto` (default: `auto`)                              | No             |
| `charset`                  | Character set of the email (mutually exclusive with nested `<message>`)                              | No             |
| `ignoreInvalidRecipients`  | Continue sending if some recipients are invalid (default: `false`)                                  | No             |
| `enableStartTLS`           | Enables STARTTLS encryption (requires JavaMail)                                                      | No             |

## Nested Elements

- `<from>` / `<replyto>` / `<to>` / `<cc>` / `<bcc>`: specify email addresses with optional `name` and required `address`.
- `<message>`: specify message body via nested text or attributes `src`, `mimetype`, `charset`, `inputencoding`.
- `<header>`: add custom email headers with `name` and `value`.
- `<attachments>`: container for attachments. Use `<fileset>`, `<path>`, or other resource collections.

## Notes

- Address attributes can include names and addresses in formats like `Name <address@domain.com>`.
- This task may require external libraries (JavaMail/JakartaMail) for SMTP auth and MIME support.
- Without JavaMail, only plain text/UU encoding is available.

## Reference

- [Ant Manual: mail Task](https://ant.apache.org/manual/Tasks/mail.html)
