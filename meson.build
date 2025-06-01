project('deeznuts', 'cpp', default_options: ['cpp_std=c++23', 'warning_level=3'], version : run_command('git', 'describe', '--tags', '--dirty', '--always', check: true).stdout().strip())

includes = include_directories('include')

subdir('tests')

project_source_files = ['src/deeznuts.cxx']

project_dependencies = []

build_args = [
  '-DPROJECT_NAME=' + meson.project_name(),
  '-DPROJECT_VERSION="' + meson.project_version() + '"',
]

executable(
  meson.project_name(),
  project_source_files,
  dependencies: project_dependencies,
  install: true,
  cpp_args: build_args,
  include_directories: includes,
)
