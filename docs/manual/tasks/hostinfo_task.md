# hostinfo

The `hostinfo` task retrieves information about a local or remote host and sets properties describing its name, domain, and IP addresses.

## Usage

Retrieve host info for the current machine:

```groovy
target('localHostInfo') {
  hostinfo()
}
```

Retrieve host info for a specific host and prefix the properties:

```groovy
target('remoteHostInfo') {
  hostinfo(prefix: 'remote', host: 'www.apache.org')
}
```

This sets:
- `remote.NAME`
- `remote.DOMAIN`
- `remote.ADDR4`
- `remote.ADDR6`

## Attributes

| Attribute | Description                                                                 | Required |
|-----------|-----------------------------------------------------------------------------|----------|
| `host`    | The hostname or IP address to query. Defaults to the local host.           | No       |
| `prefix`  | Prefix to use when setting the properties (a dot is appended automatically). | No       |

## Properties Set

- `NAME` — the short host name (e.g. `localhost`)
- `DOMAIN` — the domain portion of the host (e.g. `localdomain`)
- `ADDR4` — the most relevant IPv4 address (or `127.0.0.1`)
- `ADDR6` — the most relevant IPv6 address (or `::1`)

## Example Output

```
remote.NAME = apache
remote.DOMAIN = apache.org
remote.ADDR4 = 140.211.11.130
remote.ADDR6 = ::
```

## Notes

- If a host cannot be resolved, fallback values like `127.0.0.1` or `localhost` will be used.
- Useful for dynamically setting environment-specific properties during initialization.

## Reference

- [Ant Manual: hostinfo Task](https://ant.apache.org/manual/Tasks/hostinfo.html)
