package istio.authz

import future.keywords.if
import future.keywords.every
import future.keywords.in
import input.attributes.request.http as http_request
import input.parsed_path


default allow = false

allow {
    role := user_roles[claims.id].role
    access_get_salary_api_own(role)
}

allow {
    role := user_roles[claims.id].role
    access_get_salary_api_manager(role)
}

allow {
    role := user_roles[claims.id].role
    access_for_hr(role)
}

allow {
    role := user_roles[claims.id].role
    access_for_report(role)
}

allow {
    parsed_path[0] == "health"
    http_request.method == "GET"
}

access_get_salary_api_own(role) {
	claims.type == "user"
	perm := role_perms[role]
    perm[_].method == http_request.method
    perm[_].api == parsed_path[0]
    claims.id == parsed_path[1]
}

access_get_salary_api_manager(role) {
	claims.type == "user"
	perm := role_perms[role]
    perm[_].method == http_request.method
    perm[_].api == parsed_path[0]
    not claims.id == parsed_path[1]
    direct_reports := user_roles[claims.id].manager
    parsed_path[1] == direct_reports[_]
}

access_for_hr(role) {
	claims.type == "hr"
	perm := role_perms[role]
    perm[_].method == http_request.method
    perm[_].api == parsed_path[0]
}

access_for_report(role) {
	claims.type == "report"
	perm := role_perms[role]
    perm[_].method == http_request.method
    perm[_].api == parsed_path[0]
}

claims := payload if {
  [valid, _, payload] := io.jwt.decode_verify(bearer_token, constraints)
  valid == true
}

bearer_token := t if {
	# Bearer tokens are contained inside of the HTTP Authorization header. This rule
	# parses the header and extracts the Bearer token value. If no Bearer token is
	# provided, the `bearer_token` value is undefined.
	v := http_request.headers.authorization
	startswith(v, "Bearer ")
	t := substring(v, count("Bearer "), -1)
}

constraints := {
  "secret": "dare-me-not-256-bit-secret",
  "alg": "HS256"
}

user_roles = {
    "alice": {"role" : "user", "manager" : []},
    "john": {"role" : "user", "manager" : []},
    "dave": {"role" : "user", "manager" : []},
    "bob": {"role" : "user", "manager": ["john", "dave"]},
    "carol": {"role" : "user", "manager": ["alice"]},
    "jim" : {"role" : "hr", "manager" : []},
    "reports" : {"role" : "report-app", "manager" : []}
}

role_perms = {
    "user": [
        {"method": "GET",  "api": "getSalary"}
    ],
    "hr": [
        {"method": "GET",  "api": "getSalaries"},
        {"method": "GET",  "api": "getSalary"},
        {"method": "PUT",  "api": "updateSalary"}
    ],
    "report-app": [
        {"method": "GET",  "api": "getSalary"}
    ]
}

