# coding: utf-8

# jinja
from jinja2 import Environment, PackageLoader, select_autoescape

print(PackageLoader("template", "").list_templates())
env = Environment(
    loader=PackageLoader("template", ""),
    autoescape=select_autoescape()
)

template = env.get_template('sample.html')
print(template.render(title="ninja", message="hello, ninja!"))
